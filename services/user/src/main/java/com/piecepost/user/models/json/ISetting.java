package com.piecepost.user.models.json;

import lombok.Data;

@Data
public class ISetting {
    private boolean allowMessage = true;
    private boolean showOnlineStatus = true;
    private String theme = "default";
}
