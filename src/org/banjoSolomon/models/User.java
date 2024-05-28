package org.banjoSolomon.models;

public class User {
    private Long id;
    private Long walletId;

    public Long getWalletId() {
        return walletId;
    }

    public void setWalletId(Long wallet) {
        walletId = wallet;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
