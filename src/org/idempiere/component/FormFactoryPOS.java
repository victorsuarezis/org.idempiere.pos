package org.idempiere.component;

import java.util.logging.Level;

import org.adempiere.base.equinox.EquinoxExtensionLocator;
import org.adempiere.ui.swing.factory.IFormFactory;
import org.compiere.apps.form.FormPanel;
import org.compiere.util.CLogger;

public class FormFactoryPOS implements IFormFactory {
	
	protected transient CLogger log = CLogger.getCLogger(getClass());

	public FormFactoryPOS() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public FormPanel newFormInstance(String formName) {
		log.info(formName);
		FormPanel form = EquinoxExtensionLocator.instance().locate(FormPanel.class, "org.adempiere.pos", formName, null).getExtension();
		if (form == null) {
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			Class<?> clazz = null;
			if (loader != null) {
				try {
					clazz = loader.loadClass(formName);
				} catch (Exception e) {
					if (log.isLoggable(Level.INFO)) {
						log.log(Level.INFO, e.getLocalizedMessage(), e);
					}
				}
			}
			if (clazz == null) {
				loader = this.getClass().getClassLoader();
				try {
					clazz = loader.loadClass(formName);
				} catch (Exception e) {
					if (log.isLoggable(Level.INFO)) {
						log.log(Level.INFO, e.getLocalizedMessage(), e);
					}
				}
			}
			if (clazz != null) {
				try {
					form = (FormPanel) clazz.newInstance();
				} catch (Exception e) {
					if (log.isLoggable(Level.WARNING)) {
						log.log(Level.WARNING, e.getLocalizedMessage(), e);
					}
				}
			}
		}
		return form;
	}
}