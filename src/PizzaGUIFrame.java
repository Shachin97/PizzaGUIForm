import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;

public class PizzaGUIFrame extends JFrame
{
    JPanel mainPnl;
    JPanel sizePnl;
    JPanel toppingPnl;
    JPanel crustTypePnl;
    JPanel controlPnl;
    JPanel receiptPnl;

    JPanel horizontalPnl;

    JComboBox sizes;

    JCheckBox pineappleCB;
    JCheckBox sourCB;
    JCheckBox pepperoniCB;
    JCheckBox chickenCB;
    JCheckBox sausageCB;
    JCheckBox bloodCB;


    JRadioButton thinCrust;
    JRadioButton regCrust;
    JRadioButton deepDishCrust;

    JTextArea orderDetails;
    ButtonGroup types;
    JButton quitBtn;
    JButton clearBtn;
    JButton order;
    JScrollPane pane;

    JLabel sizePrices, smallPrice, mediumPrice, largePrice, superPrice;

    int toppingTotal;
    double sizePrice;
    double tax = .07;
    double totalBill;

    public PizzaGUIFrame()
    {

        setSize(600, 700);
        setTitle("Pizza Order");
        createGui();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    private void createGui()
    {
        mainPnl = new JPanel();
        mainPnl.setLayout(new BoxLayout(mainPnl, BoxLayout.PAGE_AXIS));

        horizontalPnl = new JPanel();

        horizontalPnl.setLayout(new GridLayout(1,2));
        sizePnl = new JPanel();
        crustTypePnl = new JPanel();
        horizontalPnl.add(sizePnl);
        horizontalPnl.add(crustTypePnl);

        mainPnl.add(horizontalPnl);

        toppingPnl = new JPanel();
        mainPnl.add(toppingPnl);

        receiptPnl = new JPanel();
        mainPnl.add(receiptPnl);

        controlPnl = new JPanel();
        mainPnl.add(controlPnl);



        add(mainPnl);
        createCrustTypePnl();
        createSizePnl();
        createToppingPanel();
        createReceiptPln();
        createControlPanel();


    }
    private void createCrustTypePnl()
    {
        crustTypePnl.setLayout(new GridLayout(1, 3));
        crustTypePnl.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 1),"Crust Type"));

        types = new ButtonGroup();

        thinCrust  = new JRadioButton("Thin");
        regCrust= new JRadioButton("Regular");
        deepDishCrust  = new JRadioButton("Deep-Dish");


        types.add(thinCrust);
        types.add(regCrust);
        types.add(deepDishCrust);

        regCrust.setSelected(true);

        crustTypePnl.add(regCrust);
        crustTypePnl.add(thinCrust);
        crustTypePnl.add(deepDishCrust);


    }

    private void createSizePnl()
    {

        sizePnl.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 1),"Pizza Size"));

        sizes = new JComboBox();

        sizes = new JComboBox();
        sizes.addItem("Small");
        sizes.addItem("Medium");
        sizes.addItem("Large");
        sizes.addItem("Super");
        sizePnl.add(sizes);



    }



    private void createToppingPanel()
    {
        toppingPnl.setLayout(new GridLayout(3,3));
        toppingPnl.setBorder(new TitledBorder(new LineBorder(Color.BLACK,1 ),"Toppings"));

        pepperoniCB = new JCheckBox("Pepperoni");
        pineappleCB = new JCheckBox("Pineapple");
        sourCB = new JCheckBox("Sour");
        chickenCB = new JCheckBox("Chicken");
        sausageCB = new JCheckBox("Sausage");
        bloodCB = new JCheckBox("Blood");

        toppingPnl.add(pepperoniCB);
        toppingPnl.add(pineappleCB);
        toppingPnl.add(sourCB);
        toppingPnl.add(bloodCB);
        toppingPnl.add(sausageCB);
        toppingPnl.add(chickenCB);


    }
    private void createReceiptPln()
    {

        receiptPnl.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 1),"Receipt"));

        orderDetails = new JTextArea(20,23);
        orderDetails.setEditable(false);
        pane = new JScrollPane(orderDetails);
        receiptPnl.add(pane);

    }

    private void createControlPanel()
    {
        controlPnl.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 1),"Controls"));


        clearBtn = new JButton("Clear");
        clearBtn.addActionListener((ActionEvent ae) -> {

            orderDetails.setText(" ");
            tax = .07;
            sizePrice = 0;
            toppingTotal = 0;
            totalBill = 0;

            regCrust.setSelected(true);

            sizes.setSelectedIndex(1);

            pepperoniCB.setEnabled(false);
            chickenCB.setEnabled(false);
            pineappleCB.setEnabled(false);
            sourCB.setEnabled(false);
            bloodCB.setEnabled(false);
            sausageCB.setEnabled(false);

            order.setEnabled(true);

            pepperoniCB.setEnabled(true);
            chickenCB.setEnabled(true);
            pineappleCB.setEnabled(true);
            sourCB.setEnabled(true);
            bloodCB.setEnabled(true);
            sausageCB.setEnabled(true);

            sizes.setEnabled(true);

            thinCrust.setEnabled(true);
            regCrust.setEnabled(true);
            deepDishCrust.setEnabled(true);

        });

        order = new JButton("Order");
        order.addActionListener((ActionEvent ae) ->
                {
                    DecimalFormat df = new DecimalFormat("$0.00");

                    String res ="Order Details: \n";
                    res += "===========================\n";
                    res += "Crust Type: ";
                    if(thinCrust.isSelected())
                        res += "Thin Crust \n";
                    else if(regCrust.isSelected())
                        res += "Regular Crust \n";
                    else if(deepDishCrust.isSelected())
                        res += "Deep-Dish Crust\n";
                    // get the items
                    String box = (String) sizes.getSelectedItem();
                    res += "Sizes: ";
                    res += sizes.getSelectedItem() + "\t";

                    if (box.equals("Small")) {
                        sizePrice = 8;
                        res += "\t    " +df.format(sizePrice) + "\n ";
                    }
                    else if (box.equals("Medium"))
                    {
                        sizePrice = 12;
                        res += "\t    " + df.format(sizePrice) + "\n";
                    }
                    else if (box.equals("Large")) {
                        sizePrice = 16;
                        res +=  "\t    " + df.format(sizePrice) + "\n";
                    }
                    else {
                        sizePrice = 20;
                        res +=  "\t    " + df.format(sizePrice) + " \n";
                    }

                    res += "With these toppings ($1 Each): \n";


                    toppingTotal = 0;

                    if (pineappleCB.isSelected())
                    {
                        toppingTotal = toppingTotal + 1;
                    }

                    if (sourCB.isSelected())
                    {
                        toppingTotal = toppingTotal + 1;
                    }

                    if (sausageCB.isSelected())
                    {
                        toppingTotal = toppingTotal + 1;
                    }
                    if (pepperoniCB.isSelected())
                    {
                        toppingTotal = toppingTotal + 1;
                    }

                    if (chickenCB.isSelected())
                    {
                        toppingTotal = toppingTotal + 1;
                    }

                    if (bloodCB.isSelected())
                    {
                        toppingTotal = toppingTotal + 1;
                    }

                    if (pepperoniCB.isSelected())
                    {
                        res+= "Pepperoni\n";
                    }

                    if (pineappleCB.isSelected())
                    {
                        res+= "Pineapple\n";
                    }

                    if (sausageCB.isSelected())
                    {
                        res+= "Sausage\n";
                    }

                    if (bloodCB.isSelected())
                    {
                        res+= "Blood\n";
                    }
                    if (sourCB.isSelected())
                    {
                        res+= "Sour\n";
                    }

                    if (chickenCB.isSelected())
                    {
                        res+= "Chicken\n";

                    }

                    res +="Topping Total Price: \t    " +  df.format(toppingTotal) ;
                    res += "\n===========================\n";
                    res+= "\n";
                    res+= "\n";

                   double subTotal = sizePrice + toppingTotal;

                    res += "Sub-total:\t\t    " ;
                    res += df.format(subTotal) + "\n";


                    tax = subTotal * tax;

                    res += "Tax:\t\t    " ;
                    res +=  df.format(tax) +"\n";

                    totalBill = subTotal + tax;

                    res += "----------------------------------\n";

                    res += "Total:\t\t    ";
                    res += df.format(totalBill);

                    res += "\n===========================\n";

                    orderDetails.append(res);

                     order.setEnabled(false);

                    pepperoniCB.setEnabled(false);
                    chickenCB.setEnabled(false);
                    pineappleCB.setEnabled(false);
                    sourCB.setEnabled(false);
                    bloodCB.setEnabled(false);
                    sausageCB.setEnabled(false);

                    sizes.setEnabled(false);

                    thinCrust.setEnabled(false);
                    regCrust.setEnabled(false);
                    deepDishCrust.setEnabled(false);
                });

        quitBtn = new JButton("Quit!");
        quitBtn.addActionListener((ActionEvent ae) ->{

            int quit = JOptionPane.showConfirmDialog(null, "Do you want to quit?");
            if (quit == JOptionPane.YES_OPTION) {
                System.exit(0);
            } else
            {

            }

        });

        controlPnl.add(clearBtn);
        controlPnl.add(order);
        controlPnl.add(quitBtn);

    }



}

