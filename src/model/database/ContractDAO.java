/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import model.Contract;
import model.ITAsset;

/**
 *
 * @author Laptop
 */
public class ContractDAO implements IDBCUD {

    public Iterator get() {
        Connection con = DBConnection.getConnection();
        ArrayList<Contract> contracts = new ArrayList<Contract>();
        Date startDate, endDate;
        try {
            String query = "SELECT * FROM contract";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                startDate = new java.util.Date(resultSet.getDate("startDate").getTime());
                endDate = new java.util.Date(resultSet.getDate("endDate").getTime());
                Contract contract = new Contract(resultSet.getInt("hardware"), startDate, endDate, resultSet.getInt("maintenanceCost"));
                contracts.add(contract);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException sqlee) {
                sqlee.printStackTrace();
            }
        }

        return contracts.iterator();
    }

    //
    public Object get(String key) {
        Connection con = DBConnection.getConnection();
        try {

            String query = "SELECT * FROM contract where hardware =  ? ORDER  BY 1";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, Integer.parseInt(key));
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Contract contract = new Contract(resultSet.getInt("hardware"), resultSet.getDate("startDate"), resultSet.getDate("endDate"), resultSet.getInt("maintenanceCost"));

                try {
                    if(con!=null)
                    con.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                return contract;
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException sqlee) {
                sqlee.printStackTrace();
            }
        }

        return null;
    }

    public Iterator search(String searchStr) {
        Connection con = DBConnection.getConnection();
        String strings[] = searchStr.split(" "); //assuming string format is number space period of time e.g. "10 days"
        ArrayList<Contract> contracts = new ArrayList<Contract>();
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        java.sql.Date date = new java.sql.Date(gregorianCalendar.getTimeInMillis());
        String dateNow = date.toString();
        Date startDate, endDate;
        if (strings[1].equalsIgnoreCase("days")) {
            int day = gregorianCalendar.get(GregorianCalendar.DAY_OF_MONTH) + (Integer.parseInt(strings[0]));
            gregorianCalendar.set(GregorianCalendar.DAY_OF_MONTH, day);
            date = new java.sql.Date(gregorianCalendar.getTimeInMillis());
            searchStr = date.toString();
        }

        try {
            String query = "SELECT * FROM contract WHERE endDate <= ? AND endDate >=  ?" + "ORDER BY 1";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, "\'" + searchStr + "\'");
            preparedStatement.setString(2, "\'" + dateNow + "\'");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                startDate = new java.util.Date(resultSet.getDate("startDate").getTime());
                endDate = new java.util.Date(resultSet.getDate("endDate").getTime());
                Contract contract = new Contract(resultSet.getInt("hardware"), startDate, endDate, resultSet.getInt("maintenanceCost"));
                contracts.add(contract);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException sqlee) {
                sqlee.printStackTrace();
            }
        }
        return contracts.iterator();
    }

    public void add(Object object) {
        Connection con = DBConnection.getConnection();
        Contract contract = (Contract) object;
        try {

            String query = "INSERT INTO contract VALUES(?,?,?);";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, contract.getHardware());
            preparedStatement.setDate(2, new java.sql.Date(contract.getStartDate().getTime()));
            preparedStatement.setDate(3, new java.sql.Date(contract.getEndDate().getTime()));
            preparedStatement.setFloat(4, contract.getMaintenanceCost());
            preparedStatement.execute();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException sqlee) {
                sqlee.printStackTrace();
            }
        }

    }

    public void update(Object object, String origKey) {
        Connection con = DBConnection.getConnection();
        Contract contract = (Contract) object;
        try {
            String query = "UPDATE contract SET hardware = ?,startDate = ?, "
                    + "endDate= ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, contract.getHardware());
            preparedStatement.setDate(2, new java.sql.Date(contract.getStartDate().getTime()));
            preparedStatement.setDate(3, new java.sql.Date(contract.getEndDate().getTime()));
            preparedStatement.setFloat(4, contract.getMaintenanceCost());
            preparedStatement.execute();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException sqlee) {
                sqlee.printStackTrace();
            }
        }
    }

    public void delete(Object object) {
        Connection con = DBConnection.getConnection();
        Contract contract = (Contract) object;
        try {
            String query = "DELETE FROM contract WHERE ID = ?;";
            PreparedStatement preparedStatement = con
                    .prepareStatement(query);
            preparedStatement.setInt(1, contract.getHardware());
            preparedStatement.execute();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException sqlee) {
                sqlee.printStackTrace();
            }
        }
    }

}
