package com.company.business;

import com.company.dao.ContactDAO;
import com.company.dao.ContactDAOFactory;
import com.company.entity.Contact;
import com.company.exception.ContactBusinessException;
import com.company.exception.ContactDaoException;
import java.util.List;

/**
 * Класс для реализации функций над списком контактов
 */
public class ContactManager
{
    private final ContactDAO dao;

    public ContactManager() {
        dao = ContactDAOFactory.getContactDAO();
    }

    // Добавление контакта - возвращает ID добавленного контакта
    public Long addContact(Contact contact) throws ContactBusinessException {
        try {
            return dao.addContact(contact);
        } catch (ContactDaoException ex) {
            throw new ContactBusinessException(ex);
        }
    }

    // Редактирование контакта
    public void updateContact(Contact contact) throws ContactBusinessException {
        try {
            dao.updateContact(contact);
        } catch (ContactDaoException ex) {
            throw new ContactBusinessException(ex);
        }
    }

    // Удаление контакта по его ID
    public void deleteContact(Long contactId) throws ContactBusinessException {
        try {
            dao.deleteContact(contactId);
        } catch (ContactDaoException ex) {
            throw new ContactBusinessException(ex);
        }
    }

    // Получение одного контакта
    public Contact getContact(Long contactId) throws ContactBusinessException {
        try {
            return dao.getContact(contactId);
        } catch (ContactDaoException ex) {
            throw new ContactBusinessException(ex);
        }
    }

    // Получение списка контактов
    public List<Contact> findContacts() throws ContactBusinessException {
        try {
            return dao.findContacts();
        } catch (ContactDaoException ex) {
            throw new ContactBusinessException(ex);
        }
    }
}
