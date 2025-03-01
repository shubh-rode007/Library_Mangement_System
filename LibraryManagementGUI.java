
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class LibraryManagementGUI {

    private JFrame frame;
    private JTextField bookTitleField, authorField, memberNameField, memberIdField;
    private JTextArea displayArea;
    private Library library;

    public LibraryManagementGUI() {
        library = new Library();
        initialize();
    }


    public void initialize() {
        frame = new JFrame("Library Management System");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));

        JLabel bookTitleLabel = new JLabel("Book Title:");
        bookTitleField = new JTextField();
        JLabel authorLabel = new JLabel("Author:");
        authorField = new JTextField();

        JLabel memberNameLabel = new JLabel("Member Name:");
        memberNameField = new JTextField();
        JLabel memberIdLabel = new JLabel("Member ID:");
        memberIdField = new JTextField();

        panel.add(bookTitleLabel);
        panel.add(bookTitleField);
        panel.add(authorLabel);
        panel.add(authorField);
        panel.add(memberNameLabel);
        panel.add(memberNameField);
        panel.add(memberIdLabel);
        panel.add(memberIdField);

        JButton addBookButton = new JButton("Add Book");
        JButton addMemberButton = new JButton("Add Member");
        JButton issueBookButton = new JButton("Issue Book");
        JButton returnBookButton = new JButton("Return Book");

        panel.add(addBookButton);
        panel.add(addMemberButton);
        panel.add(issueBookButton);
        panel.add(returnBookButton);

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        frame.getContentPane().add(panel, BorderLayout.NORTH);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        addBookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String title = bookTitleField.getText();
                String author = authorField.getText();
                Book book = new Book(title, author);
                library.addBook(book);
                displayArea.append("Book added: " + title + "\n");
            }
        });

        addMemberButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = memberNameField.getText();
                String id = memberIdField.getText();
                Member member = new Member(name, id);
                library.addMember(member);
                displayArea.append("Member added: " + name + "\n");
            }
        });

        issueBookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String title = bookTitleField.getText();
                String memberId = memberIdField.getText();
                Book book = library.findBook(title);
                Member member = library.findMember(memberId);
                if (book != null && member != null && !book.isIssued()) {
                    book.issueBook(member);
                    displayArea.append("Book issued to: " + member.getName() + "\n");
                } else {
                    displayArea.append("Book not available or member not found.\n");
                }
            }
        });

        returnBookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String title = bookTitleField.getText();
                Book book = library.findBook(title);
                if (book != null && book.isIssued()) {
                    book.returnBook();
                    displayArea.append("Book returned: " + title + "\n");
                } else {
                    displayArea.append("This book is not issued or doesn't exist.\n");
                }
            }
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new LibraryManagementGUI();
    }
}
