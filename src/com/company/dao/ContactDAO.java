package com.company.dao;

import java.util.List;
import com.company.entity.Contact;
import com.company.exception.ContactDaoException;

/**
 * Интерфейс для определения функций хранлиза данных о контактах
 */
public interface ContactDAO
{
    // Добавление контакта - возвращает ID добавленного контакта
    public Long addContact(Contact contact) throws ContactDaoException;
    // Редактирование контакта
    public void updateContact(Contact contact) throws ContactDaoException;
    // Удаление контакта по его ID
    public void deleteContact(Long contactId) throws ContactDaoException;
    // Получение контакта
    public Contact getContact(Long contactId) throws ContactDaoException;
    // Получение списка контактов
    public List<Contact> findContacts() throws ContactDaoException;

}
