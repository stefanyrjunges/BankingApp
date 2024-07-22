import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class BankingApp implements ActionListener{

    private JFrame frame;
    private JLabel bankLabel;
    private JPanel panel, outerPanel;
    private JMenuItem balance, addMoney, removeMoney, transactions, fundsTransfer, convert;
    private double cBalance = 0.0;
    private JTextField amountField, lessAmountField, transferAmountF, recipientNF, amountEURF;
    private JButton addButton, home, removeButton, transferButton, convertB;
    private List<Double> addedMoneyTransactions = new ArrayList<>();
    private List<Double> takenMoneyTransactions = new ArrayList<>();
    private String selectedCurrency; 
    private JComboBox<String> currencyList;
    private String[] currencies = new String[4];

    public BankingApp(){
       initialize();
    }

    public void initialize(){

        //Creating the main window/frame
        frame = new JFrame();
        frame.setTitle("The Banking App. Get started today.");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setResizable(false);
        //Center it
        frame.setLocationRelativeTo(null);

        //Creating the menu bar
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(Color.BLACK);
        JMenu manage = new JMenu("Manage your account");
        manage.setForeground((new Color(255,250,250)));
        JMenu transfer = new JMenu("Transfer funds");
        transfer.setForeground((new Color(255,250,250)));
        JMenu converter = new JMenu("Converter");
        converter.setForeground((new Color(255,250,250)));

        //Creating the items in the menu bar
        balance = new JMenuItem("Balance");
        addMoney = new JMenuItem("Add money");
        removeMoney = new JMenuItem("Remove money");
        transactions = new JMenuItem("Previous transactions");
        fundsTransfer = new JMenuItem("New transfer");
        convert = new JMenuItem("New conversion");

        balance.setBackground(Color.BLACK);
        balance.setForeground((new Color(255,250,250)));
        addMoney.setBackground(Color.BLACK);
        addMoney.setForeground((new Color(255,250,250)));
        removeMoney.setBackground(Color.BLACK);
        removeMoney.setForeground((new Color(255,250,250)));
        transactions.setBackground(Color.BLACK);
        transactions.setForeground((new Color(255,250,250)));
        fundsTransfer.setBackground(Color.BLACK);
        fundsTransfer.setForeground((new Color(255,250,250)));
        convert.setBackground(Color.BLACK);
        convert.setForeground((new Color(255,250,250)));

        //Adding the items
        manage.add(balance);
        manage.add(addMoney);
        manage.add(removeMoney);
        manage.add(transactions);
        transfer.add(fundsTransfer);
        converter.add(convert);

        menuBar.add(manage);
        menuBar.add(transfer);
        menuBar.add(converter);

        frame.setJMenuBar(menuBar);

        //Creating action listener
        balance.addActionListener(this);
        addMoney.addActionListener(this);
        removeMoney.addActionListener(this);
        transactions.addActionListener(this);
        fundsTransfer.addActionListener(this);
        convert.addActionListener(this);

        //Creating an outer panel to center the panel
        outerPanel = new JPanel(new GridBagLayout());
        outerPanel.setBackground(new Color(255,250,250));

        //Creating the main panel
        panel = new JPanel();
        //Horizontal and vertical in pixels. And components are centered
        panel.setPreferredSize(new Dimension(400, 300));
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panel.setBackground(new Color(220,220,220));

        // JButton button = new JButton("Click me");
        // panel.add(button, BorderLayout.NORTH);

        //Creating the label for the Banking App
        bankLabel = new JLabel("<html><br>The Banking App. Get started today. It's that easy.<hr></html>");
        bankLabel.setFont(new Font("Dialog", Font.BOLD, 15));
        panel.add(bankLabel);


        //panel.add(new JLabel(new ImageIcon("logor.png")));

        outerPanel.add(panel);

        frame.add(outerPanel, BorderLayout.CENTER);

        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        panel.removeAll();
        panel.revalidate();
        panel.repaint();
        JLabel cLabel = new JLabel();
        JLabel title = new JLabel();
        title.setFont(new Font("Dialog", Font.PLAIN, 30));
        cLabel.setFont(new Font("Dialog", Font.PLAIN, 15));
        panel.add(title);
        panel.add(cLabel);

        //Home button inside items to go back to Homepage
        if (e.getSource() == home){
            panel.add(bankLabel);
        }

        //Balance item
        if (e.getSource() == balance) {
            title.setText("<html>Balance<hr><br><br></html>");
            cLabel.setText("Your current balance is €" + cBalance);
        }

        //Add money item
        if (e.getSource() == addMoney) {
            title.setText("<html>Add money<hr><br></html>");
            cLabel.setText("Please input the amount you'd like to add");
            amountField = new JTextField("", 25);
            //Button
            addButton = new JButton("Add+ to my account");
            addButton.addActionListener(this);
            //Adding elements
            panel.add(amountField);
            panel.add(Box.createVerticalStrut(40));
            panel.add(addButton);
            cLabel.setText("Your current balance is €" + cBalance);
        } 
        
        //Add money button inside add money item
        if (e.getSource() == addButton) {
            String getValue = amountField.getText();
            JLabel errorLabel = new JLabel(); //put this in private later
            home = new JButton("Home");
            home.addActionListener(this);
            try {
                getValue = getValue.replace(',', '.');
                double getValueC = Double.parseDouble(getValue);
                title.setText("<html>All done!<hr><br></html>");
                JLabel addedMoney = new JLabel("We'd like to confirm that you added €" + getValueC + " to your account.");
                cBalance += getValueC;
                addedMoneyTransactions.add(getValueC);
                panel.add(addedMoney);
                panel.add(home);
            } catch (NumberFormatException ex) {
                errorLabel.setText("Invalid amount. Please enter a valid number.");
                panel.add(errorLabel);
                panel.add(home);
            }
            panel.revalidate();
            panel.repaint();
        }

        //Remove money item
        if (e.getSource() == removeMoney) {
            title.setText("<html>Remove money<hr><br></html>");
            cLabel.setText("Please input the amount you'd like taken off of your account");
            lessAmountField = new JTextField("", 25);
            //Button
            removeButton = new JButton("Remove from my account");
            removeButton.addActionListener(this);
            //Adding elements
            panel.add(lessAmountField);
            panel.add(Box.createVerticalStrut(40));
            panel.add(removeButton);
            cLabel.setText("Your current balance is €" + cBalance);
        }

        //Remove button inside remove money item
        if (e.getSource() == removeButton) {
            String getLessValue = lessAmountField.getText();
            getLessValue = getLessValue.replace(',', '.');
            double getLessValueC = Double.parseDouble(getLessValue);
            JLabel errorLabel = new JLabel();
            home = new JButton("Home");
            home.addActionListener(this);

                //If the balance is less than the amount to be removed:
                if (cBalance < getLessValueC){
                    errorLabel.setText("Your balance is too low.");
                    panel.add(errorLabel);
                    panel.add(Box.createVerticalStrut(40));
                    panel.add(home);
                } else {
                    try {
                        title.setText("<html>All done!<hr><br></html>");
                        JLabel takenMoney = new JLabel("We'd like to confirm that you've taken €" + getLessValueC + " from your account.");
                        cBalance -= getLessValueC;
                        //Adding value to arraylist
                        takenMoneyTransactions.add(getLessValueC);
                        panel.add(takenMoney);
                        panel.add(home);
                    } catch (NumberFormatException ex) {
                        errorLabel.setText("Invalid amount. Please enter a valid number.");
                        panel.add(errorLabel);
                        panel.add(home);
                    }
                }

            panel.revalidate();
            panel.repaint();
        }

        //Transaction item
        if (e.getSource() == transactions){
            title.setText("<html>Your past transactions<hr><br></html>");

            panel.add(Box.createVerticalStrut(50));
    
            JLabel addedMoneyTitle = new JLabel("Added Money Transactions:");
            addedMoneyTitle.setFont(new Font("Dialog", Font.BOLD, 16));
            panel.add(addedMoneyTitle);
    
                //If the arraylist is empty:
                if (addedMoneyTransactions.isEmpty()){
                    JLabel noTransactionLabel = new JLabel("No transactions.");
                    panel.add(noTransactionLabel);
                } else {
                    for (int i = 0; i < addedMoneyTransactions.size(); i++) {
                        double amount = addedMoneyTransactions.get(i);
                        JLabel transactionLabel = new JLabel(" Added: " + amount + "");
                        panel.add(transactionLabel);
                
                    }
                }
    
            panel.add(Box.createVerticalStrut(50));
    
            JLabel takenMoneyTitle = new JLabel("Taken Money Transactions:");
            takenMoneyTitle.setFont(new Font("Dialog", Font.BOLD, 16));
            panel.add(takenMoneyTitle);
    
                //If the arraylist is empty:
                if (takenMoneyTransactions.isEmpty()){
                    JLabel noTransactionLabel = new JLabel("No transactions.");
                    panel.add(noTransactionLabel);
                } else {
                    for (int i = 0; i < takenMoneyTransactions.size(); i++) {
                        double amount = takenMoneyTransactions.get(i);
                        JLabel transactionLabel = new JLabel(" Taken: " + amount + "");
                        panel.add(transactionLabel);
                    }
                }
    
            panel.revalidate();
            panel.repaint();
        }

        //New transfer item
        if (e.getSource() == fundsTransfer) {
            title.setText("<html><br>Transfer funds - nice and easy.<hr><br></html>");
            title.setFont(new Font("Dialog", Font.PLAIN, 25));
            JLabel recipientNL = new JLabel("Recipient's name:");
            panel.add(recipientNL);
            panel.add(Box.createVerticalStrut(40));
            recipientNF = new JTextField(25); // Use the class member
            panel.add(recipientNF);
            panel.add(Box.createVerticalStrut(40));
            JLabel recipientBL = new JLabel("Recipient's bank account number:");
            panel.add(recipientBL);
            panel.add(Box.createVerticalStrut(40));
            JTextField recipientBF = new JTextField(25);
            panel.add(recipientBF);
            panel.add(Box.createVerticalStrut(40));
            JLabel transferAmountL = new JLabel("Amount to be transfered:");
            panel.add(transferAmountL);
            panel.add(Box.createVerticalStrut(40));
            transferAmountF = new JTextField(25);
            panel.add(transferAmountF);
            panel.add(Box.createVerticalStrut(40));
            transferButton = new JButton("Send"); 
            transferButton.addActionListener(this);
            panel.add(transferButton);
        }
        
        // Transfer button action handling
        if (e.getSource() == transferButton) {
            double getTransfer = Double.parseDouble(transferAmountF.getText());
        
            if (cBalance < getTransfer) {
                JLabel warning = new JLabel("<html><br>Sorry.<br> You have insufficient funds for this transaction.<br> Check your balance for more information.</html>");
                panel.add(warning);
                panel.revalidate();
                panel.repaint();
            } else {
                String transferAmount = transferAmountF.getText();
                String recipientName = recipientNF.getText();
                title.setText("<html>All done!<hr><br></html>");
                JLabel transactionConfirm = new JLabel("We'd like to confirm you transferred €" + transferAmount + " to " + recipientName + "!");
                panel.add(transactionConfirm);
            
                panel.revalidate();
                panel.repaint();
            }
        }

        if (e.getSource() == convert) {
            title.setText("<html><br>From EUR to the world!<br> Check the latest exchange rates.<hr><br></html>");
            title.setFont(new Font("Dialog", Font.PLAIN, 20));
            String[] currencies = { "USD", "GPD", "BRL", "RUPEE", "CHILEAN PESO" };
            currencyList = new JComboBox<>(currencies);
            JLabel amountEUR = new JLabel("Amount to be converted:");
            amountEURF = new JTextField(25);
            convertB = new JButton("Convert");
            convertB.addActionListener(this);

            panel.add(currencyList);
            panel.add(amountEUR);
            panel.add(amountEURF);
            panel.add(convertB);
            panel.revalidate();
            panel.repaint();

        }

        if (e.getSource() == convertB) {
            try {
                double getEUR = Double.parseDouble(amountEURF.getText());
                selectedCurrency = (String) currencyList.getSelectedItem();
        
                JLabel conversion = new JLabel();
                conversion.setFont(new Font("Dialog", Font.PLAIN, 20));
        
                if (selectedCurrency.equals("USD")) {
                    conversion.setText("<html>Considering our latest conversion rate,<br> you'll get " + (getEUR * 1.08) + " dollars.</html>");
                } else if (selectedCurrency.equals("GPD")) {
                    conversion.setText("<html>Considering our latest conversion rate,<br> you'll get " + (getEUR * 0.85) + " pounds.</html>");
                } else if (selectedCurrency.equals("BRL")) {
                    conversion.setText("<html>Considering our latest conversion rate,<br> you'll get " + (getEUR * 5.94) + " reais.</html>");
                } else if (selectedCurrency.equals("RUPEE")) {
                    conversion.setText("<html>Considering our latest conversion rate,<br> you'll get " + (getEUR * 90.41) + " rupees.</html>");
                } else if (selectedCurrency.equals("CHILEAN PESO")) {
                    conversion.setText("<html>Considering our latest conversion rate,<br> you'll get " + (getEUR * 1.011) + " pesos.</html>");
                }
        
                panel.add(Box.createVerticalStrut(100));
                panel.add(conversion);
                panel.revalidate();
                panel.repaint();
            } catch (NumberFormatException ex) {
                JLabel errorLabel = new JLabel("Invalid amount. Please enter a valid number.");
                panel.add(errorLabel);
                panel.revalidate();
                panel.repaint();
            }
        }

        }
}