package librarysystem;

import business.*;
import business.SystemController;
import business.ControllerInterface;
import librarysystem.rulesets.RuleSet;
import librarysystem.rulesets.RuleSetFactory;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;


public class AddBookCopyWindow extends JFrame implements LibWindow, ControllerInterface {
    public static final AddBookCopyWindow INSTANCE = new AddBookCopyWindow();
    private JTextArea textArea;

    private ControllerInterface bookInterface = new SystemController();
    private JPanel bottomPPanel = new JPanel(new BorderLayout());

    private JSplitPane splitPaneOuter;

    public static String title = "Add Book Copy";

    private JLabel headerLabel = new JLabel(title);

    private JPanel leftAlignPanel = new JPanel();
    private JPanel rightAlignPanel = new JPanel();

    // memberID
    private JLabel isbnLabel = new JLabel("ISBN");
    private JTextField isbnField = new JTextField(10);

    // middle
    private JPanel middlePanel = new JPanel(new FlowLayout());
    private JPanel middleWrapperPanel = new JPanel(new BorderLayout());

    private JButton button = new JButton("Add Copy");
    private JPanel bottomPanel = new JPanel();
    private JPanel mainPanel = new JPanel(new BorderLayout());
    private boolean isInitialized = false;

    private AddBookCopyWindow() {
    }

    @Override
    public void init() {

        if(isInitialized){
            return;
        }

        setSize(1000,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        updateHeaderLabel();
        setupLeftAlignPanel();
        setupRightAlignPanel();
        setupTextArea();
        setupButton();
        mainPanel.add(middleWrapperPanel,BorderLayout.CENTER);
        middleWrapperPanel.add(middlePanel,BorderLayout.NORTH);
        setTitle(title);
        isInitialized(true);
        setUpVerticalPane();
        getContentPane().add(splitPaneOuter);

    }

    private void setUpVerticalPane() {
        splitPaneOuter = new JSplitPane(
                JSplitPane.VERTICAL_SPLIT,
                mainPanel,
                textArea);
        splitPaneOuter.setDividerLocation(getHeight()*3/4);
        add(splitPaneOuter);
    }

    private void setupTextArea() {
        textArea = new JTextArea("Welcome to the Group 2: Library Management System v1.0.0!");
        textArea.setMaximumSize(new Dimension(500,10));
        Util.adjustLabelFont(textArea,Util.DARK_BLUE,true);
        bottomPPanel.add(textArea,BorderLayout.NORTH);
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

        middleWrapperPanel.add(bottomPanel,BorderLayout.CENTER);

        JButton backButton = new JButton("<= Back to Main");
        backButton.addActionListener(evt -> {
            LibrarySystem.hideAllWindows();

            LibrarySystem.INSTANCE.setVisible(true);
        });
        bottomPanel.add(backButton);

        button.setSize(100,30);
        button.addActionListener((evt) -> {
            try {
                RuleSet rules = RuleSetFactory.getRuleSet(AddBookCopyWindow.this);
                rules.applyRules(AddBookCopyWindow.this);
                Book book = bookInterface.addBookCopyByISBN(getISBN());
                showInfo("");
                JOptionPane.showMessageDialog(this,"Copy of the book is added successfully.");
                AllBookWindow.INSTANCE.updateAvailableCountRecord(book);
            } catch (Group2Exception e) {
                showError(e.getMessage());
            }
        });
        bottomPanel.add(button);
    }

    private void setupLeftAlignPanel() {
        leftAlignPanel.setLayout(new BoxLayout(leftAlignPanel,BoxLayout.Y_AXIS));

        Component[] items = new Component[]{
                isbnLabel,
               
        };

        for (Component item: items) {
            leftAlignPanel.add(item);
            leftAlignPanel.add(Box.createRigidArea(new Dimension(0, 18)));
        }

        middlePanel.add(leftAlignPanel);
    }

    private void setupRightAlignPanel() {
        rightAlignPanel.setLayout(new BoxLayout(rightAlignPanel,BoxLayout.Y_AXIS));
        Component[] items = new Component[]{
                isbnField,
              
        };
        for (Component item: items) {
            rightAlignPanel.add(item);
            rightAlignPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        }

        middlePanel.add(rightAlignPanel);
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

    public String getISBN() {
        return isbnField.getText().trim();
    }
}
