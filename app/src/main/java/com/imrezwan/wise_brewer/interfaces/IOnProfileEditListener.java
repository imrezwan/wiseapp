package com.imrezwan.wise_brewer.interfaces;

import com.imrezwan.wise_brewer.models.ProfileFactory;

public interface IOnProfileEditListener {
    void onEditButtonClicked(ProfileFactory.ProfileInfo profileInfo);
}
