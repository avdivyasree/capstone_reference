package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
	
	public static final ExtentReports extentReports = new ExtentReports();
	
    public synchronized static ExtentReports createExtentReports() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("./test-output/extent-reports/extent-report.html");
        reporter.config().setReportName("Medicare Test Extent Report");
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Project", "Simplilearn Capstone");
        extentReports.setSystemInfo("Author", "BehCS");
        return extentReports;
    }

}
