package com.gabrielhamartins.backend_challenge.constant;

import java.util.Arrays;
import java.util.List;

public interface ConstantUtil {

    class Restrictions {
        public static final List<String> VALID_CLAIMS = Arrays.asList("Name", "Role", "Seed");
        public static final List<String> VALID_ROLES = Arrays.asList("Admin", "Member", "External");
        public static final int CLAIM_NAME_MAX_SIZE = 256;
    }

    class Messages {
        public static final String INVALID_JWT = "JWT format is not valid.";
        public static final String INVALID_CLAIMS = "Claim set is not valid.";
        public static final String INVALID_CLAIM_NAME_FORMAT = "Claim Name is not valid for having digits.";
        public static final String INVALID_CLAIM_NAME_SIZE = "Claim Seed is not valid for not being prime.";
        public static final String INVALID_CLAIM_ROLES = "Claim Role is not valid.";
        public static final String INVALID_CLAIM_SEED = "Claim Seed is not valid for not being prime.";
    }

    class Fields {
        public static final String CLAIM_ROLE = "Role";
        public static final String CLAIM_NAME = "Name";
        public static final String CLAIM_SEED = "Seed";
    }
}
