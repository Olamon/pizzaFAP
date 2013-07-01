package window.search;

import javax.swing.JButton;

import objects.Oferta;
import objects.Pizzeria;

import window.search.SearchWindow;
import window.search.PizzeriaSearchForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import states.User;

public class UserMenu extends SearchWindow {
	
	//model, który okienko informuje co należy zrobić
	//(na razie nic nie umie) 
	User model;
	
	public UserMenu(final User model) {
		this.model = model;
		final SearchWindow that = this;
		
		initComponents();
		
		refresh();
		
		pizzeriaAdvancedSearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PizzeriaSearchForm pizzeriaSearchForm = 
					new PizzeriaSearchForm("Wyszukaj pizzerię", that, model);
				pizzeriaSearchForm.setVisible(true);
			}
		});
		pizzaAdvancedSearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PizzaSearchForm pizzaSearchForm = 
					new PizzaSearchForm("Wyszukaj pizzę", that, model);
				pizzaSearchForm.setVisible(true);
			}
		});
		pizzaReview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Oferta selected = (Oferta) that.pizzaList.getSelectedValue();
				if(selected!=null){
					ReviewForm pizzaReviewForm = new ReviewForm("Pizza "+selected.nazwa, that, model, selected.id);
					pizzaReviewForm.setVisible(true);
				}
			}
		});
		pizzeriaReview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pizzeria selected = (Pizzeria) that.pizzeriaList.getSelectedValue();
				if(selected!=null){
					ReviewForm pizzeriaReviewForm = new ReviewForm(selected.nazwa, that, model, selected.id);
					pizzeriaReviewForm.setVisible(true);
				}
			}
		});
		pizzaSeeReview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				Oferta selected = (Oferta) that.pizzaList.getSelectedValue();
				if(selected!=null){
					OcenaWindow ow = new OcenaWindow("Oceny pizzy "+selected.nazwa, model.Ocena_GetAll(selected.id), model);
					ow.setVisible(true);
				}
			}
		});
		
		pizzeriaSeeReview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				Pizzeria selected = (Pizzeria) that.pizzeriaList.getSelectedValue();
				if(selected!=null){
					OcenaWindow ow = new OcenaWindow("Oceny dla "+selected.nazwa, model.Ocena_GetAll(selected.id), model);
					ow.setVisible(true);
				}
			}
		});
		
		account1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				AccountWindow aw = new AccountWindow("Moje konto", model);
				aw.setVisible(true);
			}
		});
		
		account2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				AccountWindow aw = new AccountWindow("Moje konto", model);
				aw.setVisible(true);
			}
		});
	}
	
	public void refresh() {
		pizzeriaList.setListData(model.Pizzeria_GetAll());
		pizzaList.setListData(model.Oferta_GetAll());
	}
	
	private void initComponents() {
		super.pizzeriaToolBar.add(pizzeriaReview);
		super.pizzaToolBar.add(pizzaReview);
		super.pizzeriaToolBar.add(pizzeriaSeeReview);
		super.pizzaToolBar.add(pizzaSeeReview);
		super.pizzeriaToolBar.add(account1);
		super.pizzaToolBar.add(account2);
	}
	
	private JButton pizzeriaReview = new JButton("Oceń");
	private JButton pizzaReview = new JButton("Oceń");
	private JButton pizzeriaSeeReview = new JButton("Zobacz oceny");
	private JButton pizzaSeeReview = new JButton("Zobacz oceny");
	private JButton account1 = new JButton("Konto");
	private JButton account2 = new JButton("Konto");
}
