import { AppBar, Toolbar, Typography } from "@mui/material";
import React from "react";

const Header = () => {
  return (
    <AppBar position="relative">
      <Toolbar>
        <Typography
          component="h2"
          variant="h5"
          color="inherit"
          align="center"
          noWrap
          sx={{ flex: 1 }}
        >
          Karagöz Bank
        </Typography>
      </Toolbar>
    </AppBar>
  );
};

export default Header;
