package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Product;
import form.ProductForm;
import vaildator.ProductVaildator;

public class SaveProductController implements Controller {

	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		ProductForm productForm = new ProductForm();
		productForm.setName(request.getParameter("name"));
        productForm.setDescription(
                request.getParameter("description"));
        productForm.setPrice(request.getParameter("price"));
        
        ProductVaildator productValidator = new ProductVaildator();
        List<String> errors = productValidator.validate(productForm);
        if(errors.isEmpty()){
        	// create model
            Product product = new Product();
            product.setName(productForm.getName());
            product.setDescription(productForm.getDescription());
            try {
            	product.setPrice(Float.parseFloat(
            			productForm.getPrice()));
            } catch (NumberFormatException e) {
            }
         // code to save product
            
            // store model in a scope variable for the view
            request.setAttribute("product", product);
            
    		return "/WEB-INF/jsp/ProductDetails.jsp";
        }else{
        	request.setAttribute("errors", errors);
        	request.setAttribute("form", productForm);
        	return "/WEB-INF/jsp/ProductForm.jsp";
        }       
	}

}
