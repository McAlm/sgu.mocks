package de.sgu.mocks;

import java.sql.Connection;

public class EarningsReportGenerator {

    private ConnectionProvider connectionProvider;
    private EarningsReportDataCollector earningsReportDataCollector;
    private ReportProvider reportProvider;

    public EarningsReportGenerator(ConnectionProvider connectionProvider, EarningsReportDataCollector earningsReportDataCollector, ReportProvider reportProvider) {
        this.connectionProvider = connectionProvider;
        this.earningsReportDataCollector = earningsReportDataCollector;
        this.reportProvider = reportProvider;
    }

    public Report generateReport(String mall) {
        Connection connection = this.connectionProvider.getConnection(mall);
        Earnings earnings = this.earningsReportDataCollector.getEarnings(connection);
        return this.reportProvider.createReport(earnings);
    }

}
