/*******************************************************************************
 * Copyright (c) 2016, 2018 IBM Corporation and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * This is an implementation of an early-draft specification developed under the Java
 * Community Process (JCP) and is made available for testing and evaluation purposes
 * only. The code is not compatible with any specification of the JCP.
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jdt.internal.corext.dom;

import org.eclipse.jdt.core.dom.AST;

public interface IASTSharedValues {

	/**
	 * This value is subject to change with every release. JDT-UI-internal code typically supports
	 * the latest available {@link AST#apiLevel() AST level} exclusively.
	 */
	public static final int SHARED_AST_LEVEL= AST.JLS11;

	public static final boolean SHARED_AST_STATEMENT_RECOVERY= true;

	public static final boolean SHARED_BINDING_RECOVERY= true;
}
