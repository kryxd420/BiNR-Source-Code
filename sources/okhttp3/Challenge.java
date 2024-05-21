package okhttp3;

import okhttp3.internal.Util;

public final class Challenge {
    private final String realm;
    private final String scheme;

    public Challenge(String str, String str2) {
        this.scheme = str;
        this.realm = str2;
    }

    public String scheme() {
        return this.scheme;
    }

    public String realm() {
        return this.realm;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Challenge) {
            Challenge challenge = (Challenge) obj;
            return Util.equal(this.scheme, challenge.scheme) && Util.equal(this.realm, challenge.realm);
        }
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (899 + (this.realm != null ? this.realm.hashCode() : 0)) * 31;
        if (this.scheme != null) {
            i = this.scheme.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return this.scheme + " realm=\"" + this.realm + "\"";
    }
}
