package reflection.tester;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class TestRunner {

    private List<String> results = new ArrayList<>();
    private final Logger logger = Logger.getLogger(TestRunner.class.getName());

    public void runTests(List<String> testClassNames) {
        for (String testClassName : testClassNames) {
            try {
                Class<?> testClass = Class.forName(testClassName);
                Object testObject = testClass.getDeclaredConstructor().newInstance();
                Method[] methods = testClass.getDeclaredMethods();
                for (Method method : methods) {
                    if (method.isAnnotationPresent(MyTest.class)) {
                        processTestMethod(testObject, method);
                    }
                }
            } catch (Exception e) {
                logger.warning("An error occurred: " + e.getMessage());
            }
        }
    }

    private void processTestMethod(Object testObject, Method method) {
        MyTest annotation = method.getAnnotation(MyTest.class);
        try {
            method.invoke(testObject);
            if (annotation.expected() == MyTest.None.class) {
                results.add(method.getName() + "() - OK");
            } else {
                results.add(method.getName() + "() - FAILED");
            }
        } catch (Exception e) {
            if (annotation.expected().isAssignableFrom(e.getCause().getClass())) {
                results.add(method.getName() + "() - OK");
            } else {
                results.add(method.getName() + "() - FAILED");
            }
        }
    }

    public String getResult() {
        StringBuilder resultBuilder = new StringBuilder();
        for (String result : results) {
            resultBuilder.append(result).append("\n");
        }
        return resultBuilder.toString();
    }
}
