import { ApolloProvider } from "@apollo/client";
import client from "./services/api";

import Routes from "./router";
import "./styles/global.css";

const App: React.FC = () => {
  return (
    <ApolloProvider client={client}>
      <div className="App">
        <Routes />
      </div>
    </ApolloProvider>
  );
};

export default App;
