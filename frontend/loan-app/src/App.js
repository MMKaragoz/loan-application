import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Header from "./components/UI/Header/Header";

import HomePage from "./components/UI/HomePage/HomePage";
import PersonalLoanApplication from "./components/UI/LoanApplication/PersonalLoanApplication";

function App() {
  return (
    <Router>
      <Header />
      <Routes>
        <Route exact path="/" element={<HomePage />} />
        <Route
          path="/personal-loan-application"
          element={<PersonalLoanApplication />}
        />
      </Routes>
    </Router>
  );
}

export default App;
