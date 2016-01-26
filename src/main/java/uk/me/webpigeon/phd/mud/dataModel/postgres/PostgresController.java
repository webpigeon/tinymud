package uk.me.webpigeon.phd.mud.dataModel.postgres;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import uk.me.webpigeon.phd.mud.dataModel.DataController;
import uk.me.webpigeon.phd.mud.modules.accounts.AccountModel;
import uk.me.webpigeon.phd.mud.modules.items.InventoryModel;

public class PostgresController implements DataController {
	private static final String CONNECTION_STRING = "jdbc:postgresql://%s/%s";

	private Connection connection;

	public PostgresController(String host, String db, String username, String password) throws SQLException {
		String connStr = String.format(CONNECTION_STRING, host, db);
		Connection conn = DriverManager.getConnection(connStr, username, password);
		connection = conn;
	}

	@Override
	public AccountModel getAccountModel() throws SQLException {
		return new AccountDBModel(connection);
	}

	@Override
	public void init() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public InventoryModel getInventoryModel() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}