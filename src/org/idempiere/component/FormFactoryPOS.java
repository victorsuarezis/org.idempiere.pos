/******************************************************************************
 * Product: iDempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) Trek Global All Rights Reserved.                             *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *                                                                            *
 * @author Ing. Victor Suarez - <victor.suarez.is@gmail.com> - 2017           *
 *****************************************************************************/
package org.idempiere.component;

import java.util.logging.Level;

import org.adempiere.base.equinox.EquinoxExtensionLocator;
import org.adempiere.ui.swing.factory.IFormFactory;
import org.compiere.apps.form.FormPanel;
import org.compiere.util.CLogger;

/**
 * Form Factory for POS iDempiere
 * @autor Ing. Victor Suarez - victor.suarez.is@gmail.com 
 */
public class FormFactoryPOS implements IFormFactory {
	
	protected transient CLogger log = CLogger.getCLogger(getClass());

	public FormFactoryPOS() {
		// TODO Auto-generated constructor stub
	}

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