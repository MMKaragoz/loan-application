import { Grid, TextField, Typography } from "@mui/material";
import React, { useState } from "react";

const IDInformation = (props) => {
  const [idNumber, setIdNumber] = useState(props.formData.idNumber || "");
  const [birthDate, setBirthDate] = useState(props.formData.birthDate || "");
  const [phoneNumber, setPhoneNumber] = useState(
    props.formData.phoneNumber || ""
  );

  const handleIdNumberChange = (event) => {
    setIdNumber(event.target.value);
    props.setFormData({
      ...props.formData,
      idNumber: event.target.value,
    });
  };

  const handleBirthDateChange = (event) => {
    setBirthDate(event.target.value);
    props.setFormData({
      ...props.formData,
      birthDate: event.target.value,
    });
  };

  const handlePhoneNumberChange = (event) => {
    setPhoneNumber(event.target.value);
    props.setFormData({
      ...props.formData,
      phoneNumber: event.target.value,
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
            id="idNumber"
            name="idNumber"
            label="ID Number"
            fullWidth
            autoComplete="given-name"
            variant="standard"
            value={idNumber}
            onChange={handleIdNumberChange}
            error={idNumber !== "" && !idNumber.match("^[0-9]{10}[02468]$")}
            helperText={
              idNumber !== "" && !idNumber.match("^[0-9]{10}[02468]$")
                ? "Id Number must only be numbers, the length must be 11 and last digit must be even."
                : " "
            }
          />
        </Grid>
        <Grid item xs={12} sm={12}>
          <TextField
            required
            type="date"
            id="standard-size-small"
            name="birthDate"
            label="Birth Date"
            fullWidth
            autoComplete="family-name"
            variant="standard"
            value={birthDate}
            onChange={handleBirthDateChange}
          />
        </Grid>
        <Grid item xs={12} sm={12}>
          <TextField
            required
            id="phoneNumber"
            name="phoneNumber"
            label="Phone Number"
            fullWidth
            autoComplete="family-name"
            variant="standard"
            value={phoneNumber}
            onChange={handlePhoneNumberChange}
          />
        </Grid>
      </Grid>
    </>
  );
};

export default IDInformation;
