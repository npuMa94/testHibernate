package com.npuMa.test;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.npuMa.test.models.ClothesEntity;
import com.npuMa.test.models.CustomerEntity;
import com.npuMa.test.services.ClothesServiceImpl;
import com.npuMa.test.services.CustomerServiceImpl;

public class MainWindow {

	protected Shell shell;
	private Table customersTable;
	private Table clothesTable;

	public static void main(String[] args) {
		try {
			MainWindow window = new MainWindow();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	private void createContents() {
		shell = new Shell();
		shell.setSize(500, 300);
		shell.setText("Dry cleaning");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		TabFolder tabFolder = new TabFolder(shell, SWT.NONE);
		TabItem tbtmCustomers = new TabItem(tabFolder, SWT.NONE);
		tbtmCustomers.setText("Customers");
		TabItem tbtmClothes = new TabItem(tabFolder, SWT.NONE);
		tbtmClothes.setText("Clothes");
		SashForm sashCustomers = new SashForm(tabFolder, SWT.NONE);
		tbtmCustomers.setControl(sashCustomers);
		SashForm sashClothes = new SashForm(tabFolder, SWT.NONE);
		tbtmClothes.setControl(sashClothes);
		
		Composite compTableCustomers = new Composite(sashCustomers, SWT.NONE);
		compTableCustomers.setLayout(new FillLayout(SWT.HORIZONTAL));
		Composite compButtonsCustomers = new Composite(sashCustomers, SWT.NONE);
		compButtonsCustomers.setLayout(new FillLayout(SWT.VERTICAL));
		Composite compTableClothes = new Composite(sashClothes, SWT.NONE);
		compTableClothes.setLayout(new FillLayout(SWT.HORIZONTAL));
		Composite compButtonsClothes = new Composite(sashClothes, SWT.NONE);
		compButtonsClothes.setLayout(new FillLayout(SWT.VERTICAL));
		
		createTableCustomers(compTableCustomers);
		updateTableCustomers();
		createCustomersButtons(compButtonsCustomers);
		sashCustomers.setWeights(new int[] {350, 150});

		createTableClothes(compTableClothes);
		updateTableClothes();
		createClothesButtons(compButtonsClothes);
		sashClothes.setWeights(new int[] {350, 150});
	}
	private void createTableCustomers(Composite composite) {
		String[] titles = { "ID", "NAME", "PHONE"};
		customersTable = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION | SWT.V_SCROLL | SWT.H_SCROLL);
		customersTable.setLinesVisible(true);
		customersTable.setHeaderVisible(true);
		
		for (int i = 0; i < titles.length; i++) {
			TableColumn column = new TableColumn(customersTable, SWT.CENTER);
			column.setText(titles[i]);
		}
		composite.addControlListener(new ControlAdapter() {
			public void controlResized(ControlEvent e) {
				Rectangle area = composite.getClientArea();
				int width = area.width - 2 * customersTable.getBorderWidth();
				customersTable.getColumn(0).setWidth(width / 3);
				customersTable.getColumn(1).setWidth(width / 3);
				customersTable.getColumn(2).setWidth(width / 3);
			}
		});
		
	}
	private void updateTableCustomers() {
		List<CustomerEntity> customers;
		CustomerServiceImpl customerService = new CustomerServiceImpl();
		customers = customerService.findAllCustomers();
		customersTable.setItemCount(0);
		customersTable.clearAll();
		for(CustomerEntity cust : customers) {
			System.out.println(cust);
		    TableItem item = new TableItem(customersTable, SWT.NULL);
		    item.setText(0, Integer.toString(cust.getId()));
		    item.setText(1, cust.getName());
		    item.setText(2, cust.getPhone());
		}
	}
	private void createCustomersButtons(Composite composite) {
		
		Button btnFillCustomersT = new Button(composite, SWT.NONE);
		btnFillCustomersT.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				updateTableCustomers();
			}
		});
		btnFillCustomersT.setText("Fill table");
		
		Button btnClearCustomersT = new Button(composite, SWT.NONE);
		btnClearCustomersT.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				customersTable.setItemCount(0);
				customersTable.clearAll();
			}
		});
		btnClearCustomersT.setText("Clear table");
		Button btnInsertCustomer = new Button(composite, SWT.NONE);
		btnInsertCustomer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				CustomerEntity customer;
		        CustomerServiceImpl customerService = new CustomerServiceImpl();
		        CustomerDialog customerDialog = new CustomerDialog(shell);
		        customerDialog.open();
		        List<String> resultString = customerDialog.getInput();
		        if(!resultString.isEmpty()) {
		        	if(resultString.size() == 2 && !resultString.get(0).equals("") && !resultString.get(1).equals("")) {
		        		customer = new CustomerEntity(resultString.get(0),resultString.get(1));
				        customerService.saveCustomer(customer);
				        updateTableCustomers();
		        	}

		        }
		        
			}
		});
		btnInsertCustomer.setText("Insert customer");
		
		Button btnDeleteCustomer = new Button(composite, SWT.NONE);
		btnDeleteCustomer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				int idx = customersTable.getSelectionIndex();
				if(idx > 0) {
					TableItem item = customersTable.getItem(idx);
					CustomerEntity customer;
			        CustomerServiceImpl customerService = new CustomerServiceImpl();
					customer = customerService.findCustomer(Integer.parseInt(item.getText(0)));
			        customerService.deleteCustomer(customer);
			        updateTableCustomers();
				}
	
			}
		});
		btnDeleteCustomer.setText("Delete customer");
		
		Button btnUpdateCustomer = new Button(composite, SWT.NONE);
		btnUpdateCustomer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				int idx = customersTable.getSelectionIndex();
				if(idx > 0) {
					TableItem item = customersTable.getItem(idx);
					CustomerEntity customer;
			        CustomerServiceImpl customerService = new CustomerServiceImpl();
					customer = customerService.findCustomer(Integer.parseInt(item.getText(0)));
					CustomerDialog customerDialog = new CustomerDialog(shell);
					List<String> input = new ArrayList<String>();
					input.add(customer.getName());
					input.add(customer.getPhone());
					customerDialog.setInput(input);
					customerDialog.open();
			        input = customerDialog.getInput();
			        if(!input.isEmpty()) {
			        	if(input.size() == 2 && !input.get(0).equals("") && !input.get(1).equals("")) {
			        		customer.setName(input.get(0));
			        		customer.setPhone(input.get(1));
					        customerService.updateCustomer(customer);
					        updateTableCustomers();
			        	}

			        }
			        
				}

			}
		});
		btnUpdateCustomer.setText("Update customer");
	}
	private void createTableClothes(Composite composite) {
		String[] titles = { "ID", "KIND", "PRICE", "CUST_ID"};
		clothesTable = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION | SWT.V_SCROLL | SWT.H_SCROLL);
		clothesTable.setLinesVisible(true);
		clothesTable.setHeaderVisible(true);
		
		for (int i = 0; i < titles.length; i++) {
			TableColumn column = new TableColumn(clothesTable, SWT.CENTER);
			column.setText(titles[i]);
			
		}
		composite.addControlListener(new ControlAdapter() {
			public void controlResized(ControlEvent e) {
				Rectangle area = composite.getClientArea();
				int width = area.width - 2 * clothesTable.getBorderWidth();
				clothesTable.getColumn(0).setWidth(width / 4);
				clothesTable.getColumn(1).setWidth(width / 4);
				clothesTable.getColumn(2).setWidth(width / 4);
				clothesTable.getColumn(3).setWidth(width / 4);
			}
		});
	}

	private void updateTableClothes() {
		List<ClothesEntity> clothes;
		ClothesServiceImpl clothesService = new ClothesServiceImpl();
		clothes = clothesService.findAllClothes();
		clothesTable.setItemCount(0);
		clothesTable.clearAll();
		for(ClothesEntity clE : clothes) {
			System.out.println(clE);
		    TableItem item = new TableItem(clothesTable, SWT.NULL);
		    item.setText(0, Integer.toString(clE.getId()));
		    item.setText(1, clE.getKind());
		    item.setText(2, Integer.toString(clE.getPrice()));
		    item.setText(3, Integer.toString(clE.getCustomer().getId()));
		}
	}

	private void createClothesButtons(Composite composite) {
		Button btnFillClothesT = new Button(composite, SWT.NONE);
		btnFillClothesT.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				updateTableClothes();
			}
		});
		btnFillClothesT.setText("Fill table");
		
		Button btnClearClothesT = new Button(composite, SWT.NONE);
		btnClearClothesT.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				clothesTable.setItemCount(0);
				clothesTable.clearAll();
			}
		});
		btnClearClothesT.setText("Clear table");
		
		Button btnInsertClothes = new Button(composite, SWT.NONE);
		btnInsertClothes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				ClothesEntity clothes;
		        ClothesServiceImpl clothesService = new ClothesServiceImpl();
		        CustomerServiceImpl customerService = new CustomerServiceImpl();
		        ClothesDialog clothesDialog = new ClothesDialog(shell);
		        clothesDialog.open();
		        List<String> resultString = clothesDialog.getInput();
		        if(!resultString.isEmpty()) {
		        	if(resultString.size() == 3 && !resultString.get(0).equals("") && !resultString.get(1).equals("") && !resultString.get(2).equals("")) {
		        		try{
		        			Integer.parseInt(resultString.get(1));
		        			Integer.parseInt(resultString.get(2));
		        		} catch(NumberFormatException ex) {
		        			MessageBox diag = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
		            		diag.setMessage("Incorrect input");
		            		diag.open();
		        			return;
		        		}
		        		CustomerEntity customer = customerService.findCustomer(Integer.parseInt(resultString.get(2)));
		        		if(customer != null) {
		        			clothes = new ClothesEntity(resultString.get(0), Integer.parseInt(resultString.get(1)));
		        			clothes.setCustomer(customer);
		        			clothesService.saveClothes(clothes);
		        			updateTableClothes();
		        		}       
		        	}

		        }
			}
		});
		btnInsertClothes.setText("Insert clothes");
		
		Button btnDeleteClothes = new Button(composite, SWT.NONE);
		btnDeleteClothes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				int idx = clothesTable.getSelectionIndex();
				if(idx > 0) {
					TableItem item = clothesTable.getItem(idx);
					ClothesEntity clothes;
			        ClothesServiceImpl clothesService = new ClothesServiceImpl();
					clothes = clothesService.findClothes(Integer.parseInt(item.getText(0)));
			        clothesService.deleteClothes(clothes);
			        updateTableClothes();
				}
			}
		});
		btnDeleteClothes.setText("Delete clothes");
		
		Button btnUpdateClothes = new Button(composite, SWT.NONE);
		btnUpdateClothes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				int idx = clothesTable.getSelectionIndex();
				if(idx > 0) {
					TableItem item = clothesTable.getItem(idx);
					ClothesEntity clothes;
					ClothesServiceImpl clothesService = new ClothesServiceImpl();
					clothes = clothesService.findClothes(Integer.parseInt(item.getText(0)));
					CustomerServiceImpl customerService = new CustomerServiceImpl();
			        ClothesDialog clothesDialog = new ClothesDialog(shell);
			        List<String> resultString = new ArrayList<String>();
			        resultString.add(clothes.getKind());
			        resultString.add(String.valueOf(clothes.getPrice()));
			        resultString.add(String.valueOf(clothes.getCustomer().getId()));
			    	clothesDialog.setInput(resultString);
			        clothesDialog.open();
			        resultString = clothesDialog.getInput();
			        if(!resultString.isEmpty()) {
			        	if(resultString.size() == 3 && !resultString.get(0).equals("") && !resultString.get(1).equals("") && !resultString.get(2).equals("")) {
			        		try{
			        			Integer.parseInt(resultString.get(1));
			        			Integer.parseInt(resultString.get(2));
			        		} catch(NumberFormatException ex) {
			        			MessageBox diag = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
			            		diag.setMessage("Incorrect input");
			            		diag.open();
			        			return;
			        		}
			        		CustomerEntity customer = customerService.findCustomer(Integer.parseInt(resultString.get(2)));
			        		if(customer != null) {
			        			clothes.setKind(resultString.get(0));
			        			clothes.setPrice(Integer.parseInt(resultString.get(1)));
			        			clothes.setCustomer(customer);
			        			clothesService.updateClothes(clothes);
			        			updateTableClothes();
			        		}       
			        	}

			        }
				}

			}
		});
		btnUpdateClothes.setText("Update clothes");
	}
	
}
