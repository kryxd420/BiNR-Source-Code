package net.agasper.unitynotification;

import android.os.Parcel;
import android.os.Parcelable;

public class NotificationAction implements Parcelable {
    public static final Creator CREATOR = new Creator();
    private boolean foreground;
    private String gameObject;
    private String handlerMethod;
    private String icon;
    private String identifier;
    private String title;

    public int describeContents() {
        return 0;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public void setIdentifier(String str) {
        this.identifier = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getIcon() {
        return this.icon;
    }

    public void setIcon(String str) {
        this.icon = str;
    }

    public boolean isForeground() {
        return this.foreground;
    }

    public void setForeground(boolean z) {
        this.foreground = z;
    }

    public String getGameObject() {
        return this.gameObject;
    }

    public void setGameObject(String str) {
        this.gameObject = str;
    }

    public String getHandlerMethod() {
        return this.handlerMethod;
    }

    public void setHandlerMethod(String str) {
        this.handlerMethod = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(getIdentifier());
        parcel.writeString(getTitle());
        parcel.writeString(getIcon());
        parcel.writeInt(isForeground() ? 1 : 0);
        parcel.writeString(getGameObject());
        parcel.writeString(getHandlerMethod());
    }

    private static class Creator implements Parcelable.Creator<NotificationAction> {
        private Creator() {
        }

        public NotificationAction createFromParcel(Parcel parcel) {
            NotificationAction notificationAction = new NotificationAction();
            notificationAction.setIdentifier(parcel.readString());
            notificationAction.setTitle(parcel.readString());
            notificationAction.setIcon(parcel.readString());
            boolean z = true;
            if (parcel.readInt() != 1) {
                z = false;
            }
            notificationAction.setForeground(z);
            notificationAction.setGameObject(parcel.readString());
            notificationAction.setHandlerMethod(parcel.readString());
            return notificationAction;
        }

        public NotificationAction[] newArray(int i) {
            return new NotificationAction[i];
        }
    }
}
