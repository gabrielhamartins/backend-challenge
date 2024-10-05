package com.gabrielhamartins.backend_challenge.validator;

import com.auth0.jwt.interfaces.Claim;
import com.gabrielhamartins.backend_challenge.exception.ClaimValidationException;
import com.gabrielhamartins.backend_challenge.model.ClaimError;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.gabrielhamartins.backend_challenge.constant.ConstantUtil.Fields.*;
import static com.gabrielhamartins.backend_challenge.constant.ConstantUtil.Messages.*;
import static com.gabrielhamartins.backend_challenge.constant.ConstantUtil.Restrictions.*;

@Service
public class ClaimValidator extends AbstractTokenValidator<Map<String, Claim>, ClaimError>{

    public void validate(Map<String, Claim> claims) throws ClaimValidationException {
        List<ClaimError> errors = new ArrayList<>();

        super.validate(claims, errors);
        if(!errors.isEmpty()){
            throw new ClaimValidationException(errors);
        }
    }

    @Override
    public void validateFields(Map<String, Claim> claims, List<ClaimError> errors) {
        if(claims.size() != VALID_CLAIMS.size())
            errors.add(new ClaimError(null, null, INVALID_CLAIMS));
        if(!claims.keySet().containsAll(VALID_CLAIMS))
            errors.add(new ClaimError(null, null, INVALID_CLAIMS));
        if(!VALID_ROLES.contains(claims.get(CLAIM_ROLE).asString()))
            errors.add(new ClaimError(CLAIM_ROLE, claims.get(CLAIM_ROLE).asString(), INVALID_CLAIM_ROLES));
    }

    @Override
    public void validateRules(Map<String, Claim> claims, List<ClaimError> errors) {
        if(claims.get(CLAIM_NAME).asString().matches(".*\\d.*"))
            errors.add(new ClaimError(CLAIM_NAME, claims.get(CLAIM_NAME).asString(), INVALID_CLAIM_NAME_FORMAT));
        if(claims.get(CLAIM_NAME).asString().length() > CLAIM_NAME_MAX_SIZE)
            errors.add(new ClaimError(CLAIM_NAME, claims.get(CLAIM_NAME).asString(), INVALID_CLAIM_NAME_SIZE));
        if(!BigInteger.valueOf(Long.parseLong(claims.get(CLAIM_SEED).asString())).isProbablePrime(1))
            errors.add(new ClaimError(CLAIM_SEED, claims.get(CLAIM_SEED).asString(), INVALID_CLAIM_SEED));
    }
}
