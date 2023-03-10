import {
  Button,
  Card,
  CardActionArea,
  CardActions,
  CardContent,
  CardMedia,
  Container,
  Grid,
  Typography,
} from "@mui/material";
import React from "react";
import { Link } from "react-router-dom";

const loans = [
  {
    title: "Personal Loan",
    detail: "You can apply personal loan by clicking Apply.",
  },
  {
    title: "Housing Loan",
    detail: "You can apply housing loan by clicking Apply.",
  },
  {
    title: "Vehicle Loan",
    detail: "You can apply vehicle loan by clicking Apply.",
  },
];

const HomePage = () => {
  return (
    <>
      <Container maxWidth="sm">
        <Typography
          component="h1"
          variant="h2"
          align="center"
          color="text.primary"
          gutterBottom
        >
          Loan Application
        </Typography>
        <Typography
          variant="h5"
          align="center"
          color="text.secondary"
          paragraph
        >
          The loan you need for your dreams at Karagöz Bank.
        </Typography>
      </Container>
      <Container sx={{ py: 8 }} maxWidth="md">
        <Grid container spacing={4}>
          {loans.map((loan) => (
            <Grid item xs={12} sm={6} md={4}>
              <Card sx={{ maxWidth: 345 }}>
                <CardActionArea>
                  <CardMedia
                    component="img"
                    height="140"
                    image="https://source.unsplash.com/random"
                    alt="green iguana"
                  />
                  <CardContent>
                    <Typography gutterBottom variant="h5" component="div">
                      {loan.title}
                    </Typography>
                    <Typography variant="body2" color="text.secondary">
                      {loan.detail}
                    </Typography>
                  </CardContent>
                </CardActionArea>
                <CardActions>
                  <Button
                    component={Link}
                    to={"/personal-loan-application"}
                    variant="contained"
                  >
                    Apply
                  </Button>
                </CardActions>
              </Card>
            </Grid>
          ))}
        </Grid>
      </Container>
    </>
  );
};

export default HomePage;
