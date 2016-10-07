package com.cervezoteca.anjov.presentation.view;

import com.cervezoteca.anjov.domain.model.Brewery;

import java.util.List;

/**
 * Created by anjov on 07/10/2016.
 */

public interface BreweriesView extends LoadDataView {
    void renderBreweries(List<Brewery> breweryList);
}
