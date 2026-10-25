package com.listeners;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.util.JiraPolicy;
import com.util.JiraServiceProvider;

public class TestJiraListener implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailure(ITestResult result) {

		JiraPolicy jiraPolicy = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(JiraPolicy.class);
		boolean isTicketReady = jiraPolicy.logTicketReady();
		if (isTicketReady) {
			// raise jira ticket:
			System.out.println("is ticket ready for JIRA: " + isTicketReady);
			String pwdToken = "ATATT3xFfGF0_z0BlJ05mpsirtZTRzBJXsVQbBnHE38-RpobjivTq9mppYq9EfopzwlVWUiyWdA_qcYoXzYxCyRUQZWvY1q57YiNmzoTxRob3b1amGrxHcOld7pSsCCl_9zIcPGkNzNFJRbVvgDEDvodkU47bu2D99CuHoz8ku7a5lDNXGgl6OY=2197C0CC"; 
			JiraServiceProvider jiraSp = new JiraServiceProvider("https://sivakumarit4u.atlassian.net",
					"sivakumarit4u@gmail.com", pwdToken, "SCRUM");
			String issueSummary = result.getMethod().getConstructorOrMethod().getMethod().getName()
					+ "got failed due to some assertion or exception";
			String issueDescription = result.getThrowable().getMessage() + "\n";
			issueDescription.concat(ExceptionUtils.getFullStackTrace(result.getThrowable()));

			jiraSp.createJiraTicket("Bug", issueSummary, issueDescription, "Siva");
		}

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

	}

}
