package com.addressbook;

import com.addressbook.dao.ContactDAO;
import com.addressbook.dao.ContactDAOImpl;
import com.addressbook.model.Contact;
import com.addressbook.util.CsvParser;

import java.util.List;
import java.util.Scanner;

public class AddressBookApp {

    private static final ContactDAO contactDAO =
            new ContactDAOImpl();

    private static final Scanner scanner =
            new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {

            try {

                printMenu();

                int choice =
                        Integer.parseInt(scanner.nextLine());

                switch (choice) {

                    case 1:
                        addContact();
                        break;

                    case 2:
                        viewAllContacts();
                        break;

                    case 3:
                        searchContacts();
                        break;

                    case 4:
                        updateContact();
                        break;

                    case 5:
                        deleteContact();
                        break;

                    case 6:
                        importFromCsv();
                        break;

                    case 7:
                        System.out.println(
                                "Thank You!");
                        System.exit(0);

                    default:
                        System.out.println(
                                "Invalid Choice");
                }

            } catch (Exception e) {

                System.out.println(
                        "Error : " + e.getMessage());
            }
        }
    }

    private static void printMenu() {

        System.out.println("\n===== ADDRESS BOOK =====");

        System.out.println("1. Add Contact");
        System.out.println("2. View All Contacts");
        System.out.println("3. Search Contact");
        System.out.println("4. Update Contact");
        System.out.println("5. Delete Contact");
        System.out.println("6. Import CSV");
        System.out.println("7. Exit");

        System.out.print("Enter Choice : ");
    }

    private static void addContact() throws Exception {

        System.out.print("First Name : ");
        String firstName =
                scanner.nextLine();

        System.out.print("Last Name : ");
        String lastName =
                scanner.nextLine();

        System.out.print("Phone : ");
        String phone =
                scanner.nextLine();

        System.out.print("Email : ");
        String email =
                scanner.nextLine();

        System.out.print("Address : ");
        String address =
                scanner.nextLine();

        Contact contact =
                new Contact(
                        firstName,
                        lastName,
                        phone,
                        email,
                        address
                );

        contactDAO.addContact(contact);
    }

    private static void viewAllContacts()
            throws Exception {

        List<Contact> contacts =
                contactDAO.getAllContacts();

        contacts.forEach(System.out::println);
    }

    private static void searchContacts()
            throws Exception {

        System.out.print(
                "Enter Keyword : ");

        String keyword =
                scanner.nextLine();

        List<Contact> contacts =
                contactDAO.searchContacts(keyword);

        contacts.forEach(System.out::println);
    }

    private static void updateContact()
            throws Exception {

        System.out.print("ID : ");
        int id =
                Integer.parseInt(scanner.nextLine());

        System.out.print("First Name : ");
        String firstName =
                scanner.nextLine();

        System.out.print("Last Name : ");
        String lastName =
                scanner.nextLine();

        System.out.print("Phone : ");
        String phone =
                scanner.nextLine();

        System.out.print("Email : ");
        String email =
                scanner.nextLine();

        System.out.print("Address : ");
        String address =
                scanner.nextLine();

        Contact contact =
                new Contact(
                        id,
                        firstName,
                        lastName,
                        phone,
                        email,
                        address,
                        null
                );

        contactDAO.updateContact(contact);
    }

    private static void deleteContact()
            throws Exception {

        System.out.print("Enter ID : ");

        int id =
                Integer.parseInt(scanner.nextLine());

        contactDAO.deleteContact(id);
    }

    private static void importFromCsv()
            throws Exception {

        System.out.print(
                "Enter CSV Path : ");

        String path =
                scanner.nextLine();

        List<Contact> contacts =
                CsvParser.parseContacts(path);

        contactDAO.importContactsBatch(
                contacts
        );
    }
}