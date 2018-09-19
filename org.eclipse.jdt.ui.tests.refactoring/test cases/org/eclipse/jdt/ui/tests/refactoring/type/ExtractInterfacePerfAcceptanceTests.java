/*******************************************************************************
 * Copyright (c) 2000, 2012 IBM Corporation and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jdt.ui.tests.refactoring.type;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;

import org.eclipse.test.OrderedTestSuite;
import org.eclipse.test.performance.Dimension;

import org.eclipse.ltk.core.refactoring.Refactoring;
import org.eclipse.ltk.core.refactoring.participants.ProcessorBasedRefactoring;

import org.eclipse.jdt.core.Flags;
import org.eclipse.jdt.core.IMember;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;

import org.eclipse.jdt.internal.corext.refactoring.structure.ExtractInterfaceProcessor;

import org.eclipse.jdt.ui.tests.performance.SWTTestProject;
import org.eclipse.jdt.ui.tests.refactoring.infra.RefactoringPerformanceTestCase;
import org.eclipse.jdt.ui.tests.refactoring.infra.RefactoringPerformanceTestSetup;

import org.eclipse.jdt.internal.ui.preferences.JavaPreferencesSettings;

public class ExtractInterfacePerfAcceptanceTests extends RefactoringPerformanceTestCase {

	private SWTTestProject fProject;
	private Refactoring fRefactoring;

	public static Test suite() {
		// we must make sure that cold is executed before warm
		OrderedTestSuite suite= new OrderedTestSuite(ExtractInterfacePerfAcceptanceTests.class, new String[] {
			"testExtractControl",
		});
        return new RefactoringPerformanceTestSetup(suite);
	}

	public static Test setUpTest(Test someTest) {
		return new RefactoringPerformanceTestSetup(someTest);
	}

	public ExtractInterfacePerfAcceptanceTests(String test) {
		super(test);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		fProject= new SWTTestProject();
		IType control= fProject.getProject().findType("org.eclipse.swt.widgets.Control");

		ExtractInterfaceProcessor processor= new ExtractInterfaceProcessor(control, JavaPreferencesSettings.getCodeGenerationSettings(fProject.getProject()));
		fRefactoring= new ProcessorBasedRefactoring(processor);

		IMethod[] methods= control.getMethods();
		List<IMethod> extractedMembers= new ArrayList<>();
		for (int i= 0; i < methods.length; i++) {
			IMethod method= methods[i];
			int flags= method.getFlags();
			if (Flags.isPublic(flags) && !Flags.isStatic(flags) && !method.isConstructor()) {
				extractedMembers.add(method);
			}
		}
		processor.setTypeName("IControl");
		processor.setExtractedMembers(extractedMembers.toArray(new IMember[extractedMembers.size()]));
		processor.setReplace(true);
	}

	@Override
	protected void tearDown() throws Exception {
		fProject.delete();
		super.tearDown();
	}

	public void testExtractControl() throws Exception {
		tagAsSummary("Extract Interface - Elapsed Process", Dimension.ELAPSED_PROCESS);
		executeRefactoring(fRefactoring, true);
	}
}
