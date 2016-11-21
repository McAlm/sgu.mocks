package de.sgu.mocks;

import java.sql.Connection;

public interface EarningsReportDataCollector {

    Earnings getEarnings(Connection connection);

}
