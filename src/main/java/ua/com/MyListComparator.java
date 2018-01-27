package ua.com;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Date;

import ua.com.entity.Book;

public class MyListComparator implements Comparator<Book> {

	  final String sortBy;
	    final String sortOrder;

	    public MyListComparator(String sortBy, String sortOrder) {
	        this.sortBy = sortBy;
	        this.sortOrder = sortOrder;
	    }
	
	    @Override
	    public int compare(Book b1, Book b2) {

	        try {
	            Field field1 = b1.getClass().getDeclaredField(sortBy);
	            Field field2 = b2.getClass().getDeclaredField(sortBy);

	            field1.setAccessible(true); 
	            field2.setAccessible(true);

	            if(b1.getClass().getDeclaredField(sortBy).getType() == String.class){
	            	String d1 = (String) field1.get(b1);
	            	String d2 = (String) field2.get(b2);
	            
	                return (sortOrder.equals("ASC"))? d1.compareTo(d2) : d2.compareTo(d1);

	            

	            }else if(b1.getClass().getDeclaredField(sortBy).getType() == BigDecimal.class){
	            	BigDecimal d1 = (BigDecimal) field1.get(b1);
	            	BigDecimal d2 = (BigDecimal) field2.get(b2);
	            
	                return (sortOrder.equals("ASC"))? d1.compareTo(d2) : d2.compareTo(d1);
	            }else if(b1.getClass().getDeclaredField(sortBy).getType() == Date.class){
	            	Date d1 = (Date) field1.get(b1);
	            	Date d2 = (Date) field2.get(b2);
	            
	                return (sortOrder.equals("ASC"))? d1.compareTo(d2) : d2.compareTo(d1);
	            }else{
	                String d1 = (String) field1.get(b1);
	                String d2 = (String) field2.get(b2);
	               
	                return (sortOrder.equals("ASC"))? d1.compareTo(d2) : d2.compareTo(d1);

	            }
	        } catch (SecurityException e) {
	            throw new RuntimeException(e);
	        } catch (NoSuchFieldException e) {
	            throw new RuntimeException("Missing variable sortBy");
	        }catch (ClassCastException e) {
	            throw new RuntimeException("sortBy is not found in class list");
	        } catch (IllegalArgumentException e) {
	            //shoud not happen
	            throw new RuntimeException(e);
	        } catch (IllegalAccessException e) {
	            throw new RuntimeException(e);
	        }
	    }	
	


}
