package com.company.dao;

import com.company.entity.Contact;
import com.company.exception.ContactDaoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ContactDbDAO implements ContactDAO
{
    private static final String SELECT
            = "SELECT  ID , FIRSTNAME , LASTNAME , phone, email FROM CLIENT  ORDER BY LASTNAME , LASTNAME";
    private static final String SELECT_ONE
            = "SELECT  ID , FIRSTNAME , LASTNAME , phone, email FROM CLIENT  WHERE ID=?";
    private static final String INSERT
            = "INSERT INTO CLIENT  (FIRSTNAME , LASTNAME , phone, email) VALUES (?, ?, ?, ?)";
    private static final String UPDATE
            = "UPDATE CLIENT  SET FIRSTNAME =?, LASTNAME =?, phone=?, email=? WHERE  ID =?";
    private static final String DELETE
            = "DELETE FROM CLIENT  WHERE  ID =?";

    private ConnectionBuilder builder = new SimpleConnectionBuilder();

    private Connection getConnection() throws SQLException {
        return builder.getConnection();
    }

    @Override
    public Long addContact(Contact contact) throws ContactDaoException {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(INSERT, new String[]{" ID "})) {
            Long contactId = -1L;
            pst.setString(1, contact.getFirstName());
            pst.setString(2, contact.getLastName());
            pst.setString(3, contact.getPhone());
            pst.setString(4, contact.getEmail());
            pst.executeUpdate();
            ResultSet gk = pst.getGeneratedKeys();
            if (gk.next()) {
                contactId = gk.getLong(" ID ");
            }
            gk.close();
            return contactId;
        } catch (Exception e) {
            throw new ContactDaoException(e);
        }
    }

    @Override
    public void updateContact(Contact contact) throws ContactDaoException {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(UPDATE)) {
            pst.setString(1, contact.getFirstName());
            pst.setString(2, contact.getLastName());
            pst.setString(3, contact.getPhone());
            pst.setString(4, contact.getEmail());
            pst.setLong(5, contact.getContactId());
            pst.executeUpdate();
        } catch (Exception e) {
            throw new ContactDaoException(e);
        }
    }

    @Override
    public void deleteContact(Long contactId) throws ContactDaoException {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(DELETE)) {
            pst.setLong(1, contactId);
            pst.executeUpdate();
        } catch (Exception e) {
            throw new ContactDaoException(e);
        }
    }

    @Override
    public Contact getContact(Long contactId) throws ContactDaoException {
        Contact contact = null;
        try (Connection con = getConnection()) {
            PreparedStatement pst = con.prepareStatement(SELECT_ONE);
            pst.setLong(1, contactId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                contact = fillContact(rs);
            }
            rs.close();
            pst.close();
        } catch (Exception e) {
            throw new ContactDaoException(e);
        }
        return contact;
    }

    @Override
    public List<Contact> findContacts() throws ContactDaoException {
        List<Contact> list = new LinkedList<>();
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(SELECT);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                list.add(fillContact(rs));
            }
            rs.close();
        } catch (Exception e) {
            throw new ContactDaoException(e);
        }
        return list;
    }

    private Contact fillContact(ResultSet rs) throws SQLException {
        Contact contact = new Contact();
        contact.setContactId(rs.getLong(" ID "));
        contact.setFirstName(rs.getString("FIRSTNAME"));
        contact.setLastName(rs.getString("LASTNAME "));
        contact.setPhone(rs.getString("phone"));
        contact.setEmail(rs.getString("email"));
        return contact;
    }
}
