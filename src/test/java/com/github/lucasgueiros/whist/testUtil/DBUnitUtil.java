package com.github.lucasgueiros.whist.testUtil;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.postgresql.PostgresqlDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;

public class DBUnitUtil {

	private Connection conexao;
	private DatabaseConnection conexaoDBUnit;
	private String endereco;

	public DBUnitUtil(String endereco) {
		this.endereco = endereco;

		try {
			Class.forName("org.postgresql.Driver").newInstance();
			conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/whist", "postgres", "ifpe");
			conexaoDBUnit = new DatabaseConnection(conexao);
			DatabaseConfig config = conexaoDBUnit.getConfig();
			config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new PostgresqlDataTypeFactory());
		} catch (Exception e) {
			throw new RuntimeException("Erro inicializando DBUnit", e);
		}
	}
	
	public void executeCleanInsert() {
		this.execute(DatabaseOperation.CLEAN_INSERT);
	}
	
	public void executeDelete() {
		this.execute(DatabaseOperation.DELETE_ALL);
	}

	public void execute(DatabaseOperation operation) {
		try {
			//FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
			IDataSet dataSet = new FlatXmlDataSetBuilder().setDtdMetadata(true).build(new File(endereco)); 
			//FlatXmlDataSet(, dtdMetadata);//FlatXmlDataSet(new File(endereco));

			operation.execute(conexaoDBUnit, dataSet);
		} catch (Exception e) {
			throw new RuntimeException("Erro executando DbUnit", e);
		}
	}

	public void close() {
		try {
			conexaoDBUnit.close();
			conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}