package com.tapjoy.mraid.controller;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import com.tapjoy.mraid.util.NavigationStringEnum;
import com.tapjoy.mraid.util.TransitionStringEnum;
import com.tapjoy.mraid.view.MraidView;
import java.lang.reflect.Field;

public abstract class Abstract {
    public static final String EXIT = "exit";
    public static final String FULL_SCREEN = "fullscreen";
    public static final String STYLE_NORMAL = "normal";
    protected MraidView a;
    protected Context b;

    public abstract void stopAllListeners();

    public static class PlayerProperties extends ReflectedParcelable {
        public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
            public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new PlayerProperties[i];
            }

            public final /* synthetic */ Object createFromParcel(Parcel parcel) {
                return new PlayerProperties(parcel);
            }
        };
        public boolean audioMuted;
        public boolean autoPlay;
        public boolean doLoop;
        public boolean inline;
        public boolean showControl;
        public String startStyle;
        public String stopStyle;

        public PlayerProperties() {
            this.showControl = true;
            this.autoPlay = true;
            this.audioMuted = false;
            this.doLoop = false;
            this.stopStyle = Abstract.STYLE_NORMAL;
            this.startStyle = Abstract.STYLE_NORMAL;
            this.inline = false;
        }

        public PlayerProperties(Parcel parcel) {
            super(parcel);
        }

        public void setStopStyle(String str) {
            this.stopStyle = str;
        }

        public void setProperties(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, String str, String str2) {
            this.autoPlay = z2;
            this.showControl = z3;
            this.doLoop = z5;
            this.audioMuted = z;
            this.startStyle = str;
            this.stopStyle = str2;
            this.inline = z4;
        }

        public void muteAudio() {
            this.audioMuted = true;
        }

        public boolean isAutoPlay() {
            return this.autoPlay;
        }

        public boolean showControl() {
            return this.showControl;
        }

        public boolean doLoop() {
            return this.doLoop;
        }

        public boolean doMute() {
            return this.audioMuted;
        }

        public boolean exitOnComplete() {
            return this.stopStyle.equalsIgnoreCase(Abstract.EXIT);
        }

        public boolean isFullScreen() {
            return this.startStyle.equalsIgnoreCase(Abstract.FULL_SCREEN);
        }
    }

    public static class Dimensions extends ReflectedParcelable {
        public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
            public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new Dimensions[i];
            }

            public final /* synthetic */ Object createFromParcel(Parcel parcel) {
                return new Dimensions(parcel);
            }
        };
        public int height;
        public int width;
        public int x;
        public int y;

        public Dimensions() {
            this.x = -1;
            this.y = -1;
            this.width = -1;
            this.height = -1;
        }

        protected Dimensions(Parcel parcel) {
            super(parcel);
        }
    }

    public static class Properties extends ReflectedParcelable {
        public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
            public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new Properties[i];
            }

            public final /* synthetic */ Object createFromParcel(Parcel parcel) {
                return new Properties(parcel);
            }
        };
        public int backgroundColor;
        public float backgroundOpacity;
        public boolean isModal;
        public boolean lockOrientation;
        public boolean useBackground;
        public boolean useCustomClose;

        protected Properties(Parcel parcel) {
            super(parcel);
        }

        public Properties() {
            this.useBackground = false;
            this.backgroundColor = 0;
            this.backgroundOpacity = 0.0f;
            this.isModal = false;
            this.lockOrientation = false;
            this.useCustomClose = false;
        }
    }

    public Abstract(MraidView mraidView, Context context) {
        this.a = mraidView;
        this.b = context;
    }

    public static class ReflectedParcelable implements Parcelable {
        public int describeContents() {
            return 0;
        }

        public ReflectedParcelable() {
        }

        protected ReflectedParcelable(Parcel parcel) {
            Field[] declaredFields = getClass().getDeclaredFields();
            int i = 0;
            while (i < declaredFields.length) {
                try {
                    Field field = declaredFields[i];
                    Class<?> type = field.getType();
                    if (type.isEnum()) {
                        String cls = type.toString();
                        if (cls.equals("class com.tapjoy.mraid.util.NavigationStringEnum")) {
                            field.set(this, NavigationStringEnum.fromString(parcel.readString()));
                        } else if (cls.equals("class com.tapjoy.mraid.util.TransitionStringEnum")) {
                            field.set(this, TransitionStringEnum.fromString(parcel.readString()));
                        }
                    } else if (!(field.get(this) instanceof Parcelable.Creator)) {
                        field.set(this, parcel.readValue((ClassLoader) null));
                    }
                    i++;
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                    return;
                } catch (IllegalAccessException e2) {
                    e2.printStackTrace();
                    return;
                }
            }
        }

        public void writeToParcel(Parcel parcel, int i) {
            Field[] declaredFields = getClass().getDeclaredFields();
            int i2 = 0;
            while (i2 < declaredFields.length) {
                try {
                    Field field = declaredFields[i2];
                    Class<?> type = field.getType();
                    if (type.isEnum()) {
                        String cls = type.toString();
                        if (cls.equals("class com.tapjoy.mraid.util.NavigationStringEnum")) {
                            parcel.writeString(((NavigationStringEnum) field.get(this)).getText());
                        } else if (cls.equals("class com.tapjoy.mraid.util.TransitionStringEnum")) {
                            parcel.writeString(((TransitionStringEnum) field.get(this)).getText());
                        }
                    } else {
                        Object obj = field.get(this);
                        if (!(obj instanceof Parcelable.Creator)) {
                            parcel.writeValue(obj);
                        }
                    }
                    i2++;
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                    return;
                } catch (IllegalAccessException e2) {
                    e2.printStackTrace();
                    return;
                }
            }
        }
    }
}
