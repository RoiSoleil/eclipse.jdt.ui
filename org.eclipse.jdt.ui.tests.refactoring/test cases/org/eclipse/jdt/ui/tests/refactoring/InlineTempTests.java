package org.eclipse.jdt.ui.tests.refactoring;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.ISourceRange;
import org.eclipse.jdt.core.IType;

import org.eclipse.jdt.internal.corext.refactoring.SourceRange;
import org.eclipse.jdt.internal.corext.refactoring.base.RefactoringStatus;
import org.eclipse.jdt.internal.corext.refactoring.code.InlineTempRefactoring;

public class InlineTempTests extends RefactoringTest {

	private static final Class clazz= InlineTempTests.class;
	private static final String REFACTORING_PATH= "InlineTemp/";
	
	public InlineTempTests(String name) {
		super(name);
	}
	
	protected String getRefactoringPath() {
		return REFACTORING_PATH;
	}
	
	public static Test suite() {
		return new MySetup(new TestSuite(clazz));
	}
	
	private String getSimpleTestFileName(boolean canInline, boolean input){
		String fileName = "A_" + getName();
		if (canInline)
			fileName += input ? "_in": "_out";
		return fileName + ".java"; 
	}
	
	private String getTestFileName(boolean canInline, boolean input){
		String fileName= TEST_PATH_PREFIX + getRefactoringPath();
		fileName += (canInline ? "canInline/": "cannotInline/");
		return fileName + getSimpleTestFileName(canInline, input);
	}
	
	private String getFailingTestFileName(){
		return getTestFileName(false, false);
	}
	private String getPassingTestFileName(boolean input){
		return getTestFileName(true, input);
	}

	protected ICompilationUnit createCUfromTestFile(IPackageFragment pack, boolean canInline, boolean input) throws Exception {
		return createCU(pack, getSimpleTestFileName(canInline, input), getFileContents(getTestFileName(canInline, input)));
	}
	
	private ISourceRange getSelection(ICompilationUnit cu) throws Exception{
		String source= cu.getSource();
		int offset= source.indexOf(ExtractMethodTests.SQUARE_BRACKET_OPEN);
		int end= source.indexOf(ExtractMethodTests.SQUARE_BRACKET_CLOSE);
		return new SourceRange(offset, end - offset);
	}

	private void helper1() throws Exception{
		ICompilationUnit cu= createCUfromTestFile(getPackageP(), true, true);
		ISourceRange selection= getSelection(cu);
		InlineTempRefactoring ref= new InlineTempRefactoring(cu, selection.getOffset(), selection.getLength());
		
		RefactoringStatus result= performRefactoring(ref);
		assertEquals("precondition was supposed to pass", null, result);
		
		IPackageFragment pack= (IPackageFragment)cu.getParent();
		String newCuName= getSimpleTestFileName(true, true);
		ICompilationUnit newcu= pack.getCompilationUnit(newCuName);
		assertTrue(newCuName + " does not exist", newcu.exists());
		assertEquals("incorrect inlining", getFileContents(getTestFileName(true, false)), newcu.getSource());
	}	
	
	private void helper2() throws Exception{
		ICompilationUnit cu= createCUfromTestFile(getPackageP(), false, true);
		ISourceRange selection= getSelection(cu);
		InlineTempRefactoring ref= new InlineTempRefactoring(cu, selection.getOffset(), selection.getLength());
		
		RefactoringStatus result= performRefactoring(ref);
		assertNotNull("precondition was supposed to fail", result);
	}
	
	//--- tests 
	
	public void test0() throws Exception{
		helper1();
	}

	public void test1() throws Exception{
		helper1();
	}

	public void test2() throws Exception{
		helper1();
	}
	
	public void test3() throws Exception{
		helper1();
	}

	public void test4() throws Exception{
		helper1();
	}

	public void test5() throws Exception{
		helper1();
	}

	public void test6() throws Exception{
		//printTestDisabledMessage("bug#6429 declaration source start incorrect on local variable");
		helper1();
	}

	public void test7() throws Exception{
		helper1();
	}
	
	public void test8() throws Exception{
		//printTestDisabledMessage("bug#6429 declaration source start incorrect on local variable");
		helper1();
	}
	
	public void test9() throws Exception{
		helper1();
	}
	
	public void test10() throws Exception{
//		printTestDisabledMessage("regression test for bug#9001");
		helper1();
	}

	public void testFail0() throws Exception{
//		printTestDisabledMessage("compile errors are ok now");
		helper2();
	}

	public void testFail1() throws Exception{
		helper2();
	}

	public void testFail2() throws Exception{
		helper2();
	}

	public void testFail3() throws Exception{
		helper2();
	}

	public void testFail4() throws Exception{
		helper2();
	}

	public void testFail5() throws Exception{
		helper2();
	}

	public void testFail6() throws Exception{
		helper2();
	}
	
}

