/**
 * 
 */
package com.tasly.anguo.facades.product.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.tasly.anguo.facades.populators.UnitPopulator;
import com.tasly.anguo.facades.product.UnitFacade;
import com.tasly.anguo.facades.product.data.UnitData;

import de.hybris.platform.core.model.product.UnitModel;
import de.hybris.platform.product.UnitService;

/**
 * @author i319019
 *
 */
public class DefaultUnitFacade implements UnitFacade {
	private UnitService unitService;
	private UnitPopulator unitPopulator;

	/* (non-Javadoc)
	 * @see com.tasly.anguo.facades.product.UnitFacade#getAllUnits()
	 */
	@Override
	public List<UnitData> getAllUnits() {
		Set<UnitModel> unitModels = unitService.getAllUnits();
		List<UnitData> units = new ArrayList<UnitData>();
		for (UnitModel unitModel : unitModels) {
			UnitData unit = new UnitData();
			unitPopulator.populate(unitModel, unit);
			units.add(unit);
		}
		return units;
	}

	/**
	 * @return the unitService
	 */
	public UnitService getUnitService() {
		return unitService;
	}

	/**
	 * @param unitService the unitService to set
	 */
	public void setUnitService(UnitService unitService) {
		this.unitService = unitService;
	}

	/**
	 * @return the unitPopulator
	 */
	public UnitPopulator getUnitPopulator() {
		return unitPopulator;
	}

	/**
	 * @param unitPopulator the unitPopulator to set
	 */
	public void setUnitPopulator(UnitPopulator unitPopulator) {
		this.unitPopulator = unitPopulator;
	}

}
