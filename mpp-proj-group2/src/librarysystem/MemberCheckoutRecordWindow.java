package librarysystem;

import business.*;
import business.controllers.SystemController;
import business.interfaces.ControllerInterface;
import librarysystem.rulesets.RuleSet;
import librarysystem.rulesets.RuleSetFactory;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;


public class MemberCheckoutRecordWindow extends JFrame implements LibWindow, ControllerInterface {

    private final ControllerInterface memberInterface = new SystemController();

    public static String title = "Member Checkout Record";

    public static final MemberCheckoutRecordWindow INSTANCE = new MemberCheckoutRecordWindow();
    private JTextArea textArea;
    private JPanel bottomPanel = new JPanel(new BorderLayout());

    private JSplitPane splitPaneOuter;

    private JPanel memberIDPanel = new JPanel(new BorderLayout());
    private JLabel memberIDLabel = new JLabel("Member ID");
    private JTextField memberIDTextField = new JTextField(10);


    // middle
    private JPanel middleWrapperPanel = new JPanel(new BorderLayout());
    private JPanel middlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

    private JButton button = new JButton("Print");
    private JPanel buttonPanel = new JPanel();

    private JPanel mainPanel = new JPanel(new BorderLayout());

    private JLabel headerLabel = new JLabel(title);



    private boolean isInitialized = false;

    private MemberCheckoutRecordWindow() {
    }

    @Override
    public void init() {
        if (isInitialized) {
            return;
        }
        setSize(600,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        updateHeaderLabel();
        setupMemberID();
        setupButton();
        mainPanel.add(middleWrapperPanel,BorderLayout.CENTER);
        middleWrapperPanel.add(middlePanel,BorderLayout.NORTH);
        setupBackButton();
        setupTextArea();
        setTitle(title);
        setUpVerticalPane();
        getContentPane().add(splitPaneOuter);
        isInitialized(true);

    }


    private void setUpVerticalPane() {
        splitPaneOuter = new JSplitPane(
                JSplitPane.VERTICAL_SPLIT,
                mainPanel,
                bottomPanel);
        splitPaneOuter.setDividerLocation(getHeight()/4);
        add(splitPaneOuter);
    }

    private void setupTextArea() {
        textArea = new JTextArea("Welcome to the Checkout printer!");
        textArea.setMaximumSize(new Dimension(500,10));
        Util.adjustLabelFont(textArea,Util.DARK_BLUE,true);
        bottomPanel.add(textArea,BorderLayout.NORTH);
    }

    @Override
    public boolean isInitialized() {
        return isInitialized;
    }

    @Override
    public void isInitialized(boolean val) {
        isInitialized = val;
    }

    private void updateHeaderLabel() {
        Util.adjustLabelFont(headerLabel,Util.DARK_BLUE,true);
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(headerLabel);
        mainPanel.add(panel,BorderLayout.NORTH);

    }
    private void setupButton() {
        buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.Y_AXIS));
        buttonPanel.add(button);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        button.setSize(100,30);
        button.addActionListener((evt) -> {
            try {
                RuleSet rules = RuleSetFactory.getRuleSet(MemberCheckoutRecordWindow.this);
                rules.applyRules(MemberCheckoutRecordWindow.this);
                print(getMemberID());
                clearTextFields();
                JOptionPane.showMessageDialog(this,"Please check the console log");
            }catch (Group2Exception e) {
                showError(e.getMessage());
            }
        });
        middlePanel.add(buttonPanel);
    }

    private void setupBackButton() {
        JButton backButton = new JButton("<= Back to Main");
        backButton.addActionListener(evt -> {
            LibrarySystem.hideAllWindows();

            LibrarySystem.INSTANCE.setVisible(true);
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.Y_AXIS));
        buttonPanel.add(backButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        middleWrapperPanel.add(buttonPanel,BorderLayout.SOUTH);

    }
    private void setupMemberID() {
        memberIDPanel.add(memberIDTextField,BorderLayout.NORTH);
        memberIDPanel.add(memberIDLabel,BorderLayout.SOUTH);
        middlePanel.add(memberIDPanel);
        memberIDLabel.setFont(Util.makeSmallFont(memberIDLabel.getFont()));
    }

    private void clearTextFields() {
        memberIDTextField.setText("");
    }

    @Override
    public void login(String id, String password) throws Group2Exception {

    }

    @Override
    public void showError(String string) {
        textArea.setForeground(Util.ERROR_MESSAGE_COLOR);
        textArea.setText(string);
    }

    @Override
    public void showInfo(String info) {
        textArea.setForeground(Util.INFO_MESSAGE_COLOR);
        textArea.setText(info);
    }

    @Override
    public List<LibraryMember> getAllLibraryMember() {
        return null;
    }

    @Override
    public LibraryMember createLibraryMember(String memberID, String firstName, String lastName, String street, String city, String zip, String state, String tel) throws Group2Exception {
        return null;
    }

    @Override
    public CheckoutRecord getRecord(String memberID) throws Group2Exception {
        return null;
    }

    @Override
    public List<Book> getAllBook() {
        return null;
    }

    @Override
    public Book getBookById(String isbn) throws Group2Exception {
        return null;
    }

    @Override
    public Book addBook(String ISBN, String title, int maxCheckoutLength, List<Author> authors, List<BookCopy> copies) throws Group2Exception {
        return null;
    }

    @Override
    public BookCopy addBookCopy(Book book) throws Group2Exception {
        return null;
    }

    @Override
    public void updateBookCopyStatus(Book book, BookCopy bookCopy, boolean newStatus) throws Group2Exception {

    }

    @Override
    public Book checkout(String memberID, String isbn) throws Group2Exception {
        return null;
    }

    @Override
    public Book addBookCopyByISBN(String isbn) throws Group2Exception {
        return null;
    }

    @Override
    public HashMap<BookCopy, LibraryMember> find(String isbn) throws Group2Exception {
        return null;
    }

    @Override
    public List<Author> getAllAuthor() {
        return null;
    }

    @Override
    public List<Author> getAllAuthorByBook(String ISBN) {
        return null;
    }

    private void print(String memberID) throws Group2Exception {
        CheckoutRecord record = memberInterface.getRecord(memberID);
        List<CheckoutRecordEntry> list = record.getRecordEntries();

        System.out.println("-------- Checkout Record of " + memberID + "---------");

        StringBuilder builder = new StringBuilder();
        System.out.printf("%s %-20s %-20s %-20s %-20s %n",
                "No.",
                "Book",
                "Copy No. ",
                "Checkout Date",
                "Due Date"
        );

        System.out.println("\n");

        builder.append(String.format("%s %-20s %-20s %-20s %-20s %n","No.",
                "Book",
                "Copy No. ",
                "Checkout Date",
                "Due Date"));
        builder.append("\n");
        for(int i = 0; i< list.size();i++){
            CheckoutRecordEntry recordEntry = list.get(i);
            System.out.printf("%2d. %-20s %-20s %-20s %-20s %n",  i + 1,
                    recordEntry.getBookCopy().getBook().getTitle(),
                    recordEntry.getBookCopy().getCopyNum(),
                    recordEntry.getCheckoutDate().toString(),
                    recordEntry.getDueDate().toString()
            );
            builder.append(String.format("%2d. %-20s %-20s %-20s %-20s %n",  i + 1,
                    recordEntry.getBookCopy().getBook().getTitle(),
                    recordEntry.getBookCopy().getCopyNum(),
                    recordEntry.getCheckoutDate().toString(),
                    recordEntry.getDueDate().toString()));
        }
        textArea.setText(builder.toString());
        textArea.repaint();
    }

    public String getMemberID () {
        return memberIDTextField.getText().trim();
    }

}
