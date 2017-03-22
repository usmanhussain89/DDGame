package soen.game.dd.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
/**
 * 
 * @author fyounis
 *	This is the First test Suite
 */
@RunWith(Suite.class)
@SuiteClasses({	TestMapValidation.class,
				TestGameValidation.class,
				TestItemValidation.class,
				TestCharacterValidation.class})
public class JunitTestSuite1 {

}
