/**
 * 
 */
package com.tasly.anguo.facades.product.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;

import com.tasly.anguo.core.category.AnguoCategoryService;
import com.tasly.anguo.core.enums.PackageUnit;
import com.tasly.anguo.core.location.DistrictService;
import com.tasly.anguo.core.product.strategies.CreateProductCodeStrategy;
import com.tasly.anguo.facades.product.AnguoProductFacade;

import de.hybris.platform.catalog.enums.ArticleApprovalStatus;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.chinaaccelerator.services.model.location.DistrictModel;
import de.hybris.platform.classification.ClassificationService;
import de.hybris.platform.classification.features.Feature;
import de.hybris.platform.classification.features.FeatureList;
import de.hybris.platform.classification.features.FeatureValue;
import de.hybris.platform.commercefacades.product.impl.DefaultProductFacade;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.product.UnitModel;
import de.hybris.platform.europe1.model.PriceRowModel;
import de.hybris.platform.ordersplitting.WarehouseService;
import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.product.UnitService;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.model.ModelService;

/**
 * @author i319019
 *
 */

@SuppressWarnings({ "unchecked", "rawtypes" })
public class DefaultAnguoProductFacade extends DefaultProductFacade implements
		AnguoProductFacade {
	private AnguoCategoryService anguoCategoryService;
	private ModelService modelService;
	private DistrictService districtService;
	private UnitService unitService;
	private WarehouseService warehouseService;
	private ClassificationService classificationService;
	private CreateProductCodeStrategy createProductCodeStrategy;
	private CommonI18NService commonI18NService;

	/**
	 * @return the anguoCategoryService
	 */
	public AnguoCategoryService getAnguoCategoryService()
	{
		return anguoCategoryService;
	}

	/**
	 * @param anguoCategoryService
	 *            the anguoCategoryService to set
	 */
	public void setAnguoCategoryService(
			AnguoCategoryService anguoCategoryService)
	{
		this.anguoCategoryService = anguoCategoryService;
	}

	/**
	 * @return the modelService
	 */
	public ModelService getModelService()
	{
		return modelService;
	}

	/**
	 * @param modelService
	 *            the modelService to set
	 */
	public void setModelService(ModelService modelService)
	{
		this.modelService = modelService;
	}

	/**
	 * @return the districtService
	 */
	public DistrictService getDistrictService()
	{
		return districtService;
	}

	/**
	 * @param districtService
	 *            the districtService to set
	 */
	public void setDistrictService(DistrictService districtService)
	{
		this.districtService = districtService;
	}

	/**
	 * @return the unitService
	 */
	public UnitService getUnitService()
	{
		return unitService;
	}

	/**
	 * @param unitService
	 *            the unitService to set
	 */
	public void setUnitService(UnitService unitService)
	{
		this.unitService = unitService;
	}

	/**
	 * @return the warehouseService
	 */
	public WarehouseService getWarehouseService()
	{
		return warehouseService;
	}

	/**
	 * @param warehouseService
	 *            the warehouseService to set
	 */
	public void setWarehouseService(WarehouseService warehouseService)
	{
		this.warehouseService = warehouseService;
	}

	/**
	 * @return the classificationService
	 */
	public ClassificationService getClassificationService()
	{
		return classificationService;
	}

	/**
	 * @param classificationService
	 *            the classificationService to set
	 */
	public void setClassificationService(
			ClassificationService classificationService)
	{
		this.classificationService = classificationService;
	}

	/**
	 * @return the createProductCodeStrategy
	 */
	public CreateProductCodeStrategy getCreateProductCodeStrategy()
	{
		return createProductCodeStrategy;
	}

	/**
	 * @param createProductCodeStrategy
	 *            the createProductCodeStrategy to set
	 */
	public void setCreateProductCodeStrategy(
			CreateProductCodeStrategy createProductCodeStrategy)
	{
		this.createProductCodeStrategy = createProductCodeStrategy;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tasly.anguo.facades.product.AnguoProductFacade#createProduct(java
	 * .lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, int, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * int, int, java.lang.String,
	 * de.hybris.platform.catalog.enums.ArticleApprovalStatus)
	 */
	/**
	 * @return the commonI18NService
	 */
	public CommonI18NService getCommonI18NService()
	{
		return commonI18NService;
	}

	/**
	 * @param commonI18NService
	 *            the commonI18NService to set
	 */
	public void setCommonI18NService(CommonI18NService commonI18NService)
	{
		this.commonI18NService = commonI18NService;
	}

	@Override
	public boolean createProduct(String categoryCode, String productName,
			String specification, String madeIn, String storageLocation,
			int productYear, String processMethod, String packageUnit,
			String huifen, String gandu, String containLiu, String salesUnit,
			int stock, int minSalesQuantity, int minQtd1, double price1,
			int minQtd2, double price2, int minQtd3, double price3,
			String description, ArticleApprovalStatus status)
	{
		if (StringUtils.isBlank(categoryCode))
		{
			// TODO : fail to create a product without a given category
			return false;
		} else
		{
			CategoryModel category = anguoCategoryService
					.getCategoryForCode(categoryCode);
			CatalogVersionModel catalogVersion = category.getCatalogVersion();
			ProductModel product = new ProductModel();
			List<CategoryModel> categories = new ArrayList<CategoryModel>();
			categories.add(category);
			product.setSupercategories(categories);
			product.setCatalogVersion(catalogVersion);
			String productCode = createProductCodeStrategy
					.generateProductCode();
			product.setCode(productCode);
			if (StringUtils.isNotBlank(productName))
			{
				// product.setName(productName);
				product.setName(productName, Locale.CHINESE);
			}
			if (StringUtils.isNotBlank(specification))
			{
				// product.setSpecification(specification);
				product.setSpecification(specification, Locale.CHINESE);
			}
			if (StringUtils.isNotBlank(madeIn))
			{
				DistrictModel district = districtService
						.getDistrictByCode(madeIn);
				product.setMadeIn(district);
			}
			if (StringUtils.isNotBlank(storageLocation))
			{
				DistrictModel district = districtService
						.getDistrictByCode(storageLocation);
				product.setStorageLocation(district);
			}
			if (productYear > 0)
			{
				Calendar year = Calendar.getInstance();
				year.set(productYear, 0, 0, 0, 0, 0);
				Date yearDate = year.getTime();
				product.setProduceYear(yearDate);
			}
			if (StringUtils.isNotBlank(packageUnit))
			{
				PackageUnit packUnit = PackageUnit.valueOf(packageUnit);
				product.setPackageUnit(packUnit);
			}
			if (StringUtils.isNotBlank(salesUnit))
			{
				UnitModel unit = unitService.getUnitForCode(salesUnit);
				product.setUnit(unit);
			}
			if (stock > 0)
			{
				StockLevelModel stockModel = new StockLevelModel();
				stockModel.setAvailable(stock);
				stockModel.setProduct(product);
				stockModel.setProductCode(productCode);
				WarehouseModel warehouse = warehouseService
						.getWarehouseForCode("anguo");
				stockModel.setWarehouse(warehouse);
				modelService.save(stockModel);
			}
			if (minSalesQuantity > 0)
			{
				product.setMinSalesQuantity(minSalesQuantity);
			}
			product.setApprovalStatus(status);
			modelService.save(product);
			createPriceRow(minQtd1, price1, product);
			createPriceRow(minQtd2, price2, product);
			createPriceRow(minQtd3, price3, product);
			if (StringUtils.isNotBlank(processMethod))
			{
				FeatureList featureList = classificationService
						.getFeatures(product);
				Feature feature = featureList
						.getFeatureByCode("anguoClassification/1.0/0001.processmethod");
				FeatureValue featureValue = new FeatureValue(processMethod);
				feature.addValue(featureValue);
				classificationService.setFeature(product, feature);
			}
			if (StringUtils.isNotBlank(gandu))
			{
				FeatureList featureList = classificationService
						.getFeatures(product);
				Feature feature = featureList
						.getFeatureByCode("anguoClassification/1.0/0001.dryness");
				FeatureValue featureValue = new FeatureValue(gandu);
				feature.addValue(featureValue);
				classificationService.setFeature(product, feature);
			}
			if (StringUtils.isNotBlank(huifen))
			{
				FeatureList featureList = classificationService
						.getFeatures(product);
				Feature feature = featureList
						.getFeatureByCode("anguoClassification/1.0/0001.huifen");
				FeatureValue featureValue = new FeatureValue(huifen);
				feature.addValue(featureValue);
				classificationService.setFeature(product, feature);
			}
			if (StringUtils.isNotBlank(containLiu))
			{
				FeatureList featureList = classificationService
						.getFeatures(product);
				Feature feature = featureList
						.getFeatureByCode("anguoClassification/1.0/0001.containliu");
				FeatureValue featureValue = new FeatureValue(containLiu);
				feature.addValue(featureValue);
				classificationService.setFeature(product, feature);
			}
			modelService.save(product);
			return true;
		}
	}

	/**
	 * @param minQtd
	 * @param price
	 * @param product
	 */
	private void createPriceRow(int minQtd, double price, ProductModel product)
	{
		if (minQtd > 0 && price > 0)
		{
			PriceRowModel priceRModel = new PriceRowModel();
			priceRModel.setPrice(price);
			priceRModel.setMinqtd(Long.valueOf(minQtd));
			priceRModel.setNet(true);
			priceRModel.setUnit(product.getUnit());
			priceRModel.setUnitFactor(1);
			// CurrencyModel currency =
			// commonI18NService.getCurrency("CNY");
			CurrencyModel currency = commonI18NService.getCurrentCurrency();
			priceRModel.setCurrency(currency);
			priceRModel.setProduct(product);
			modelService.save(priceRModel);
		}
	}
}
