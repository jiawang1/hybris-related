package com.tasly.anguo.facades.converters;

import org.springframework.beans.factory.annotation.Required;

import com.tasly.anguo.core.model.ContactModel;
import com.tasly.anguo.facades.user.data.ContactData;

import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.model.ModelService;

public class ContactReverseConverter implements Converter<ContactData, ContactModel>{
   
    private ModelService modelService;

    public ModelService getModelService() {
        return modelService;
    }
    
    @Required
    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }
    @Override
    public ContactModel convert(ContactData arg0) throws ConversionException {
        ContactModel model = getModelService().create(ContactModel.class);
        convert(arg0, model);
        return model;
    }

    @Override
    public ContactModel convert(ContactData arg0, ContactModel arg1)
            throws ConversionException {
        arg1.setName(arg0.getName());
        arg1.setContactInfo(arg0.getContactInfo());
        arg1.setPosition(arg0.getPosition());
        return arg1;
    }

}
