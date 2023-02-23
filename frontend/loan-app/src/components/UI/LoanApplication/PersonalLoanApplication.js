import {
  Button,
  Paper,
  Step,
  StepLabel,
  Stepper,
  Typography,
} from "@mui/material";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import { Box, Container } from "@mui/system";
import axios from "axios";
import React, { useState } from "react";
import IDInformation from "./IDInformation/IDInformation";
import LoanApplicationDetails from "./LoanApplicationDetails/LoanApplicationDetails";
import PersonalDetails from "./PersonalDetails/PersonalDetails";
import ReviewLoanApplication from "./ReviewLoanApplication/ReviewLoanApplication";

const steps = [
  "ID Information",
  "Personal details",
  "Loan application details",
  "Review the application",
];

function getStepContent(step, formData, setFormData, errors) {
  switch (step) {
    case 0:
      console.log(formData);
      return (
        <IDInformation
          formData={formData}
          setFormData={setFormData}
          errors={errors}
        />
      );
    case 1:
      return <PersonalDetails formData={formData} setFormData={setFormData} />;
    case 2:
      return (
        <LoanApplicationDetails formData={formData} setFormData={setFormData} />
      );
    case 3:
      return <ReviewLoanApplication formData={formData} />;
    default:
      throw new Error("Unknown step");
  }
}

const theme = createTheme();

const PersonalLoanApplication = () => {
  const [activeStep, setActiveStep] = React.useState(0);
  const [formData, setFormData] = useState({
    idNumber: "",
    birthDate: "",
    phoneNumber: "",
    name: "",
    surname: "",
    email: "",
    monthlyIncome: "",
    amountOfLoan: "",
    collateral: "",
  });
  const [errors, setErrors] = useState([]);

  const handleNext = () => {
    setActiveStep(activeStep + 1);
    if (activeStep === steps.length - 1) {
      createCustomer();
    }
  };

  const createCustomer = async () => {
    const data = {
      idNumber: formData.idNumber,
      name: formData.name,
      surname: formData.surname,
      email: formData.email,
      monthlyIncome: formData.monthlyIncome,
      phoneNumber: formData.phoneNumber,
      birthDate: formData.birthDate,
    };
    try {
      console.log(data);
      await axios.post("/customers", data);
    } catch (error) {
      if (error.response) {
        console.log(error.response.data.message);
        setErrors(error.response.data.message);
      }
    }
  };

  const handleBack = () => {
    setActiveStep(activeStep - 1);
  };

  return (
    <>
      <ThemeProvider theme={theme}>
        <Container component="main" maxWidth="sm" sx={{ mb: 4 }}>
          <Paper
            variant="outlined"
            sx={{ my: { xs: 3, md: 4 }, p: { xs: 2, md: 2 } }}
          >
            <Typography component="h1" variant="h4" align="center">
              Personal Loan Application
            </Typography>
            <Stepper activeStep={activeStep} sx={{ pt: 3, pb: 5 }}>
              {steps.map((label) => (
                <Step key={label}>
                  <StepLabel>{label}</StepLabel>
                </Step>
              ))}
            </Stepper>
            <React.Fragment>
              {getStepContent(activeStep, formData, setFormData, errors)}
              <Box sx={{ display: "flex", justifyContent: "flex-end" }}>
                {activeStep !== 0 && (
                  <Button onClick={handleBack} sx={{ mt: 3, ml: 1 }}>
                    Back
                  </Button>
                )}

                <Button
                  variant="contained"
                  onClick={handleNext}
                  sx={{ mt: 3, ml: 1 }}
                >
                  {activeStep === steps.length - 1 ? "Apply Loan" : "Next"}
                </Button>
              </Box>
            </React.Fragment>
          </Paper>
        </Container>
      </ThemeProvider>
    </>
  );
};

export default PersonalLoanApplication;
