import { Link } from "react-router-dom";

export default function HomePage() {
  return (
    <div className="container text-center mt-5">
      <h1 className="text-primary display-4 fw-bold mb-3">Boocinema</h1>
      <h2 className="text-muted mb-4"><i>I migliori film di sempre!</i></h2>
      <Link to="/films" className="btn btn-lg btn-outline-primary">
        Vai alla lista
      </Link>
    </div>
  );
}