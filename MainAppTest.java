import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;

public class MainAppTest {

	public static void main(String[] args) {
		TestListenerAdapter tla = new TestListenerAdapter();
		TestNG testng = new TestNG();
		testng.setTestClasses(new Class[] { Test01_GET.class });
		testng.addListener(tla);
		testng.run();
		}
}