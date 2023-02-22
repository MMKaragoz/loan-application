import { Grid, TextField, Typography } from "@mui/material";
import React, { useState } from "react";

const LoanApplicationDetails = (props) => {
  const [amountOfLoan, setAmountOfLoan] = useState(
    props.formData.amountOfLoan || 0.0
  );
  const [collateral, setCollateral] = useState(
    props.formData.collateral || 0.0
  );

  const handleAmountOfLoanChange = (event) => {
    setAmountOfLoan(event.target.value);
    props.setFormData({
      ...props.formData,
      amountOfLoan: event.target.value,
    });
  };

  const handleCollateralChange = (event) => {
    setCollateral(event.target.value);
    props.setFormData({
      ...props.formData,
      collateral: event.target.value,
    });
  };
  return (
    <>
      <Typography variant="h6" gutterBottom>
        ID Information
      </Typography>
      <Grid container spacing={3}>
        <Grid item xs={12} sm={12}>
          <TextField
            required
            id="amountOfLoan"
            name="amountOfLoan"
            label="Amount of Loan"
            fullWidth
            autoComplete="given-name"
            variant="standard"
            value={amountOfLoan}
            onChange={handleAmountOfLoanChange}
          />
        </Grid>
        <Grid item xs={12} sm={12}>
          <TextField
            required
            id="collateral"
            name="collateral"
            label="Collateral"
            fullWidth
            autoComplete="given-name"
            variant="standard"
            value={collateral}
            onChange={handleCollateralChange}
          />
        </Grid>
      </Grid>
    </>
  );
};

export default LoanApplicationDetails;
