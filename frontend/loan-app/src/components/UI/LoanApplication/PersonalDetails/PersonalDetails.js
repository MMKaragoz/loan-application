import { Grid, TextField, Typography } from "@mui/material";
import React, { useState } from "react";

const PersonalDetails = (props) => {
  const [name, setName] = useState(props.formData.name || "");
  const [surname, setSurname] = useState(props.formData.surname || "");
  const [email, setEmail] = useState(props.formData.email || "");
  const [monthlyIncome, setMonthlyIncome] = useState(
    props.formData.monthlyIncome || ""
  );

  const handleNameChange = (event) => {
    setName(event.target.value);
    props.setFormData({
      ...props.formData,
      name: event.target.value,
    });
  };

  const handleSurnameChange = (event) => {
    setSurname(event.target.value);
    props.setFormData({
      ...props.formData,
      surname: event.target.value,
    });
  };

  const handleEmailChange = (event) => {
    setEmail(event.target.value);
    props.setFormData({
      ...props.formData,
      email: event.target.value,
    });
  };

  const handleMonthlyIncomeChange = (event) => {
    setMonthlyIncome(event.target.value);
    props.setFormData({
      ...props.formData,
      monthlyIncome: event.target.value,
    });
  };
  return (
    <>
      <Typography variant="h6" gutterBottom>
        Personal Details
      </Typography>
      <Grid container spacing={3}>
        <Grid item xs={12} sm={12}>
          <TextField
            required
            id="name"
            name="name"
            label="Name"
            fullWidth
            autoComplete="given-name"
            variant="standard"
            value={name}
            onChange={handleNameChange}
          />
        </Grid>
        <Grid item xs={12} sm={12}>
          <TextField
            required
            id="surname"
            name="surname"
            label="Surname"
            fullWidth
            autoComplete="given-name"
            variant="standard"
            value={surname}
            onChange={handleSurnameChange}
          />
        </Grid>
        <Grid item xs={12} sm={12}>
          <TextField
            required
            id="email"
            name="email"
            label="Email"
            fullWidth
            autoComplete="given-name"
            variant="standard"
            value={email}
            onChange={handleEmailChange}
          />
        </Grid>
        <Grid item xs={12} sm={12}>
          <TextField
            required
            id="monthlyIncome"
            name="monthlyIncome"
            label="Monthly Income"
            fullWidth
            autoComplete="given-name"
            variant="standard"
            value={monthlyIncome}
            onChange={handleMonthlyIncomeChange}
          />
        </Grid>
      </Grid>
    </>
  );
};

export default PersonalDetails;
