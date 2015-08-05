package com.tasly.anguo.facades.converters;

import com.tasly.anguo.core.model.ContactModel;
import com.tasly.anguo.facades.user.data.ContactData;

import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

public class ContactData2ModelConverter implements Converter<ContactData, ContactModel>{

    @Override
    public ContactModel convert(ContactData arg0) throws ConversionException {
        ContactModel model = new ContactModel();
        convert(arg0, model);
        return model;
    }

    @Override
    public ContactModel convert(ContactData arg0, ContactModel arg1)
            throws ConversionException {
        arg1.setName(arg0.getName());
        arg1.setContactInfo(arg0.getContactInfo());
        arg1.setPosistion(arg0.getPosition());
        return arg1;
    }

}
