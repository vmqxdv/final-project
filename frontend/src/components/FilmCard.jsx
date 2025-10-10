import { Link } from "react-router-dom";

export default function FilmCard({ film }) {
  const { id, name, image } = film;

  return (
    <Link to={`/films/${id}`}>
      <div className="card h-100 shadow-sm border-0">
        <img src={image} className="card-img-top" alt={name} style={{ height: '25rem', objectFit: 'cover' }} />
        <div className="card-body text-center">
          <h5 className="card-title">{name}</h5>
        </div>
      </div>
    </Link>
  );
};