package org.bluemix.challenge.backend;


import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;



/**
 *
 */
@SuppressWarnings("serial")
public class Customer implements Serializable, Cloneable {
    
    public enum Gender {
        Male, Female, Other
    }
    
    private Long id;
    
    private String firstName, lastName, email, phone, address, city, zipCode;
    
    private Gender gender;
    
    private Date birthDate;
    
    private int annualIncome;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
    
    public int getAge()
    {
    	Calendar dob = Calendar.getInstance();  
    	dob.setTime(this.birthDate);  
    	Calendar today = Calendar.getInstance();  
    	int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);  
    	if (today.get(Calendar.MONTH) < dob.get(Calendar.MONTH)) 
    	{
    	  age--;  
    	} 
    	else if (today.get(Calendar.MONTH) == dob.get(Calendar.MONTH)
    	    && today.get(Calendar.DAY_OF_MONTH) < dob.get(Calendar.DAY_OF_MONTH)) 
    	{
    	  age--;  
    	}
    	return age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if(this.id == null) {
            return false;
        }

        if (obj instanceof Customer && obj.getClass().equals(getClass())) {
            return this.id.equals(((Customer) obj).id);
        }

        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + (id == null ? 0 : id.hashCode());
        return hash;
    }

    @Override
    public Customer clone() throws CloneNotSupportedException {
        return (Customer) super.clone();
    }

    @Override
    public String toString() {
        return firstName + " "+ lastName;
    }
    
    public String toSmallName() 
    {
    	return firstName.substring(0,1) + ". " + lastName;
    }

	public int getAnnualIncome()
	{
		return annualIncome;
	}

	public void setAnnualIncome(int annualIncome)
	{
		this.annualIncome = annualIncome;
	}
    
}
