package com.emitrom.easygwt.client.resources;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface SampleImages extends ClientBundle {

    @Source("images/my_crazy_looking_pc.png")
    ImageResource crazyLookingPc();

    @Source("images/left_banner.png")
    ImageResource leftBanner();

    @Source("images/right_banner.png")
    ImageResource rightBanner();
    
    @Source("images/login_dialog.png")
    ImageResource loginDialog();

}
