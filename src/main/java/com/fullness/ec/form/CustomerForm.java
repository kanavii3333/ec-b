package com.fullness.ec.form;

import java.time.ZonedDateTime;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CustomerForm {
   @NotBlank
   @Length(min=2,max=20, message = "{length.customerName}")
   @Pattern(regexp = "^[a-zA-Z0-9()\u4E00-\u9FAF\u3040-\u309F\u30A0-\u30FF]+$", message = "{pattern.customerName}")
   private String customerName; 

   @NotBlank
   @Length(min=2,max=20, message = "{length.customerNameKana}")
   @Pattern(regexp = "^[ァ-ヶー ]+$", message = "{pattern.customerNameKana}")
   private String customerNameKana; 

   @NotBlank
   @Length(min=0,max=100, message = "{length.customerAddress}")
   @Pattern(regexp = "^[A-Za-z0-9!\"#$%&'()*+,-./:;<=>?@\\[\\]^_`{|}~ぁ-ゖァ-ヶ一-龯]+$", message = "{pattern.customerAddress}")
   private String customerAddress1; 

   @Length(min=0,max=100, message = "{length.customerAddress}")
   private String customerAddress2; 

   @NotBlank
   @Length(min=0,max=14, message = "{length.customerPhoneNumber}")
   @Pattern(regexp = "^\\d{2}-\\d{4}-\\d{4}$", message = "{pattern.customerPhoneNumber}")
   private String customerPhoneNumber; 

   @NotBlank
   @Length(min=4,max=100, message = "{length.customerMailAddress}")
   @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "{pattern.customerMailAddress}")
   private String customerMailAdress;

   @NotBlank
   @Length(min=5,max=20, message = "{length.customerUsername}")
   @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "{pattern.customerUsername}")
   private String customerUsername; 

   @NotBlank
   @Length(min=5,max=20, message = "{length.customerPassword}")
   @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "{pattern.customerPassword}")
   private String customerPassword; 
   private ZonedDateTime customerRegisterDate; 
}
