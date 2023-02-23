import { List, ListItem, ListItemText, Typography } from "@mui/material";
import React from "react";

const ReviewLoanApplication = (props) => {
  const { formData } = props;
  return (
    <>
      <Typography variant="h6" gutterBottom>
        Review Your Loan Application
      </Typography>
      <List disablePadding>
        {Object.keys(formData).map((key) => (
          <ListItem key={key} sx={{ py: 1, px: 0 }}>
            <ListItemText primary={key} />
            <Typography variant="body2">{formData[key]}</Typography>
          </ListItem>
        ))}
      </List>
    </>
  );
};

export default ReviewLoanApplication;
