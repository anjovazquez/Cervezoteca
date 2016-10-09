package com.cervezoteca.anjov.presentation.view;

import com.cervezoteca.anjov.domain.model.Brewery;
import com.cervezoteca.anjov.domain.model.TapBeer;

import java.util.List;

/**
 * Created by anjov on 07/10/2016.
 */

public interface TapBeersView extends LoadDataView {
    void renderTapBeers(List<TapBeer> beerList);
}
